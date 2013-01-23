package cuboid;

import graph.*;

import io.StringToStringArrayVertexIDParser;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author Benoit Denis
 * 
 * Simplified version
 * 1) no edges
 * 2) aggregate function = identity
 */
public class CuboidProcessor extends Configured implements Tool {
	
	public static class Map extends MapReduceBase implements Mapper<Text,Text,Text,LongWritable>{
		
		private int dimension;
		
		public void configure(JobConf job){
			dimension = Integer.parseInt(job.get("DIMENSIONS"));
		}

		public void map(Text key, Text value, OutputCollector<Text,LongWritable> output, Reporter reporter) throws IOException {
			MultiDimensionnalVertexID<?> vertexID = (MultiDimensionnalVertexID<?>)
					(new StringToStringArrayVertexIDParser(dimension)).parseID(key.toString());
			//Improvement to be implemented : leave the choice of the parser to the user
			MultiDimensionnalVertexID<?> aggregatedID = vertexID;
			
			LongWritable outputWeight = new LongWritable(Long.parseLong(value.toString()));
			
			output.collect(new Text(aggregatedID.toString()), outputWeight);
		}
	}
	
	public static class Reduce extends MapReduceBase implements Reducer<Text,LongWritable,Text,LongWritable>{
		
		public void reduce(Text key, Iterator<LongWritable> values, 
				OutputCollector<Text,LongWritable> output, Reporter reporter) throws IOException {
			long sum = 0;
			while(values.hasNext()){
				sum += values.next().get();
			}
			output.collect(new Text(key.toString()), new LongWritable(sum));
		}
	}
	
	public int run(String[]args) throws Exception{
		
		if(args.length != 4){
			printHelp();
			return -1;
		}
		
		
		JobConf conf = new JobConf(getConf(),CuboidProcessor.class);
		conf.setJobName("cuboidQuery");
		
		conf.set("DIMENSIONS", args[3]);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(LongWritable.class);
		
		conf.setMapperClass(Map.class);
		conf.setCombinerClass(Reduce.class);
		conf.setReducerClass(Reduce.class);
		
		conf.setInputFormat(KeyValueTextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(conf, new Path(args[1]));
		FileOutputFormat.setOutputPath(conf, new Path(args[2]));
		
		JobClient.runJob(conf);
		return 0;
		
	}

	private void printHelp() {
		System.out.println("arg1 : input path\narg2 : output path");
		System.out.println("arg3 : number of dimensions");
		
	}
	
	public static void main(String[]args) throws Exception{
		int res = ToolRunner.run(new Configuration(),new CuboidProcessor(),args);
		System.exit(res);
	}
}
