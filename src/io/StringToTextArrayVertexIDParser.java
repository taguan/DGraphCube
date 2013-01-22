package io;

import java.io.IOException;
import graph.*;

/**
 * Maps String input key into TextArrayVertexID
 * @author Benoit Denis
 *
 */
public class StringToTextArrayVertexIDParser extends MultiDimVertexIDParser<String,String> {

	private int dimension;
	
	public StringToTextArrayVertexIDParser(int dimension){
		this.dimension = dimension;
	}
	
	/*
	 * @param input String of the form "dim1 dim2 dim3 ..."
	 */
	public MultiDimensionnalVertexID<String> parseID(String input) throws IOException{
		String[]explode = input.split(" ");
		TextArrayVertexID vertexID = new TextArrayVertexID(explode,this.dimension);
		
		if(explode.length != this.dimension){
			System.out.println("Expected number of dimensions : " + this.dimension);
			System.out.println("Input key number of dimensions : " + explode.length);
			System.out.println("Input string : " + input);
			throw new IOException("Wrong number of dimensions in input key");
		}
		
		return vertexID;
		
	}
}
