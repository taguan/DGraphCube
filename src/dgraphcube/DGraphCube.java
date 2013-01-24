package dgraphcube;

import materialization.*;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cuboid.CuboidProcessor;

public class DGraphCube {

	private static Options getOptions(){
		Options options = new Options();
		options.addOption("h","help", false, "Display help");
		options.addOption("dm", "dontMaterialize", false, "Do not apply the materialization strategy");
		options.addOption("inp","inputPath", true, "Input path to graph");
		options.addOption("n", "dimNumber", true, "Number of dimensions");
		options.addOption("vd", "vertexDelimiter", true, "Vertex delimiter");
	    options.addOption("ed", "edgeDelimiter", true, "Edge delimiter");
		
		return options;
	}
	
	private static void printHelp(){
		HelpFormatter formatter = new HelpFormatter();
	    formatter.printHelp("DGraphCube", getOptions(), true);
	}
	
	public static void main(String[]args){
		Options options = getOptions();
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;
		try{
			cmd = parser.parse(options, args);
		}
		catch(ParseException ex){
			System.err.println(ex.getMessage());
			System.exit(-1);
		}

	    if (args.length == 0 || cmd.hasOption("h")) {
	      printHelp();
	      System.exit(0);
	    }
	    
	    if(!cmd.hasOption("inp")){
	    	System.out.println("Input path required");
	    	printHelp();
	    	System.exit(-1);
	    }
	    
	    if(!cmd.hasOption("n")){
	    	System.out.println("Number of dimensions required");
	    	printHelp();
	    	System.exit(-1);
	    }
	    
	    String [] cuboidArgs = new String[12];
	    cuboidArgs[0] = "-inp"; cuboidArgs[1] = cmd.getOptionValue("inp");
	    cuboidArgs[2] = "-n"; cuboidArgs[3] = cmd.getOptionValue("n");
	    cuboidArgs[4] = "-vd"; cuboidArgs[5] = cmd.getOptionValue("vd", ",");
	    cuboidArgs[6] = "-ed"; cuboidArgs[7] = cmd.getOptionValue("ed"," ");
	    
	    if(cmd.hasOption("dm")){
	    	//Dont materialize... search for possible already materialized aggregated graphs
	    }
	    else{
	    	MaterializationStrategy strategy = new MinStrategy(3,Integer.parseInt(cmd.getOptionValue("n")));
	    	while(!strategy.finished()){
	    		String func = strategy.nextAggregate();
	    		cuboidArgs[8] = "-f"; cuboidArgs[9] = func;
	    		cuboidArgs[10] = "-oup"; cuboidArgs[11] = func + "_" + cmd.getOptionValue("inp");
	    	}
	    }
	 
	    
	    try{
	    	CuboidProcessor.main(cuboidArgs);
	    }
	    catch(Exception ex){
	    	System.err.println(ex.getMessage());
	    	System.exit(-1);
	    }
	}
}
