package cuboid;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
 * Simplified version
 * 1) no edges
 * 2) vertex identifier = long
 * 3) aggregate function = /10
 */
public class CuboidProcessor extends Configured implements Tool {
	
	public static class Map extends MapReduceBase implements Mapper<Text,Text,LongWritable,LongWritable>{
		
		public void map(Text key, Text value, OutputCollector<LongWritable,LongWritable> output, Reporter reporter) throws IOException {
			long vertexID = Long.parseLong(key.toString());
			LongWritable aggregatedID = new LongWritable(vertexID / 10);
			
			LongWritable outputWeight = new LongWritable(Long.parseLong(value.toString()));
			
			output.collect(aggregatedID, outputWeight);
		}
	}
	
	public static class Reduce extends MapReduceBase implements Reducer<LongWritable,LongWritable,LongWritable,LongWritable>{
		
		public void reduce(LongWritable key, Iterator<LongWritable> values, 
				OutputCollector<LongWritable,LongWritable> output, Reporter reporter) throws IOException {
			long sum = 0;
			while(values.hasNext()){
				sum += values.next().get();
			}
			output.collect(key, new LongWritable(sum));
		}
	}
	
	public int run(String[]args) throws Exception{
		
		if(args.length != 3){
			printHelp();
			return -1;
		}
		
		JobConf conf = new JobConf(getConf(),CuboidProcessor.class);
		conf.setJobName("cuboidQuery");
		
		conf.setOutputKeyClass(LongWritable.class);
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
		
	}
	
	public static void main(String[]args) throws Exception{
		int res = ToolRunner.run(new Configuration(),new CuboidProcessor(),args);
		System.exit(res);
	}
}
