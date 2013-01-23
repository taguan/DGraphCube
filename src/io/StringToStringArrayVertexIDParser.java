package io;

import java.io.IOException;
import graph.*;

/**
 * Maps String input key into ArrayVertexID<String>
 * @author Benoit Denis
 *
 */
public class StringToStringArrayVertexIDParser extends MultiDimVertexIDParser<String,String> {

	private int dimension;
	
	public StringToStringArrayVertexIDParser(int dimension){
		this.dimension = dimension;
	}
	
	/*
	 * @param input String of the form "dim1 sep dim2 sep dim3 ..."
	 */
	public MultiDimensionnalVertexID parseID(String input, String separator) throws IOException{
		String[]explode = input.split(separator);
		StringDimension[]dimensions = new StringDimension[explode.length];
		for(int i = 0; i<dimensions.length; i++){
			dimensions[i] = new StringDimension(explode[i]);
		}
		ArrayVertexID vertexID = 
				new ArrayVertexID(dimensions,this.dimension);
		
		if(explode.length != this.dimension){
			System.out.println("Expected number of dimensions : " + this.dimension);
			System.out.println("Input key number of dimensions : " + explode.length);
			System.out.println("Input string : " + input);
			throw new IOException("Wrong number of dimensions in input key");
		}
		
		return vertexID;
		
	}

}
