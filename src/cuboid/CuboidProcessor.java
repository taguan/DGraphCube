package cuboid;

import graph.*;

import io.StringToStringArrayVertexIDParser;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
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
 */
public class CuboidProcessor extends Configured implements Tool {
	
	public static class Map extends MapReduceBase implements Mapper<Text,Text,Text,LongWritable>{
		
		private int dimension;
		private AggregateFunction func;
		
		public void configure(JobConf job){
			dimension = Integer.parseInt(job.get("DIMENSIONS"));
			func = new BaseAggregate();
			func.parseFunction(job.get("AGGREGATE_FUNCTION"));
		}

		public void map(Text key, Text value, OutputCollector<Text,LongWritable> output, Reporter reporter) throws IOException {
			MultiDimensionnalVertexID vertexID = 
					(new StringToStringArrayVertexIDParser(dimension)).parseID(key.toString());
			//Improvement to be implemented : leave the choice of the parser to the user
			func.aggregateVertex(vertexID);
			
			LongWritable outputWeight = new LongWritable(Long.parseLong(value.toString()));
			
			output.collect(new Text(vertexID.toString()), outputWeight);
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
	
	/**
	   * Get the options available.
	   *
	   * @return Options available.
	   */
	  private static Options getOptions() {
	    Options options = new Options();
	    options.addOption("h", "help", false, "Help");
	    options.addOption("inp", "inputPath", true, "Input path / file (HDFS)");
	    options.addOption("oup", "outputPath", true, "Output path (HDFS");
	    options.addOption("n", "numberOfDim", true, "Number of vertex dimensions");
	    options.addOption("f", "function", true, "Aggregate function :  int1,int2,...\n" +
	    		"int1, int2 being the dimensions to aggregate");
	    return options;
	  }
	
	public int run(String[]args) throws Exception{
		
		Options options = getOptions();
		
		CommandLineParser parser = new BasicParser();
	    CommandLine cmd = parser.parse(options, args);

	    if (args.length == 0 || cmd.hasOption("h")) {
	      printHelp();
	      return 0;
	    }
		
		JobConf conf = new JobConf(getConf(),CuboidProcessor.class);
		conf.setJobName("cuboidQuery");
		
		if(!cmd.hasOption("n")){
			System.err.println("Missing number of dimensions");
			printHelp();
			return -1;
		}
		conf.set("DIMENSIONS", cmd.getOptionValue("n"));
		
		if(!cmd.hasOption("f")){
			System.err.println("Missing aggregate function");
			printHelp();
			return -1;
		}
		conf.set("AGGREGATE_FUNCTION", cmd.getOptionValue("f"));
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(LongWritable.class);
		
		conf.setMapperClass(Map.class);
		conf.setCombinerClass(Reduce.class);
		conf.setReducerClass(Reduce.class);
		
		conf.setInputFormat(KeyValueTextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		
		if(!cmd.hasOption("inp")){
			System.err.println("Input path required");
			printHelp();
			return -1;
		}
		FileInputFormat.setInputPaths(conf, new Path(cmd.getOptionValue("inp")));
		
		if(!cmd.hasOption("oup")){
			System.err.println("Output path required");
			printHelp();
			return -1;
		}
		FileOutputFormat.setOutputPath(conf, new Path(cmd.getOptionValue("oup")));
		
		JobClient.runJob(conf);
		return 0;
		
	}

	private void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
	    formatter.printHelp(getClass().getName(), getOptions(), true);
	}
	
	public static void main(String[]args) throws Exception{
		int res = ToolRunner.run(new Configuration(),new CuboidProcessor(),args);
		System.exit(res);
	}
}
