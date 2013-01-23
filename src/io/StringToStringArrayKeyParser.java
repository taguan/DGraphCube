package io;

import java.io.IOException;
import graph.*;

/**
 * Maps String input key into ArrayVertexID<String>[] (length 1 for vertex, length 2 for edges)
 * @author Benoit Denis
 *
 */
public class StringToStringArrayKeyParser implements KeyParser<String> {

	private int dimension;
	
	public StringToStringArrayKeyParser(int dimension){
		this.dimension = dimension;
	}
	
	/*
	 * @param input String of the form "dim1 Vsep dim2 Esep dim1' Vsep dim2' "
	 */
	public MultiDimensionnalVertexID[] parseID(String input, String vertexSeparator,
			String edgeSeparator) throws IOException{
		String[]explode = input.split(edgeSeparator);
		MultiDimensionnalVertexID[] toReturn = null;
		
		if(explode.length == 1){ //this is a vertex
			toReturn = new MultiDimensionnalVertexID[1];
			toReturn[0] = parseVertex(explode[0],vertexSeparator);	
		}
		else{ //it is an edge
			toReturn = new MultiDimensionnalVertexID[2];
			String first;
			String second;
			
			//Makes the edge sorted (in networks, edges are undirected)
			if(explode[0].compareTo(explode[1]) < 0){
				first = explode[0];
				second = explode[1];
			}
			else{
				first = explode[1];
				second = explode[0];
			}
			
			toReturn[0] = parseVertex(first,vertexSeparator);
			toReturn[1] = parseVertex(second,vertexSeparator);
		}
		
		return toReturn;
	}
	
	private MultiDimensionnalVertexID parseVertex(String input, String vertexSeparator) 
			throws IOException{
		
		String[]explode = input.split(vertexSeparator);
		StringDimension[]dimensions = new StringDimension[explode.length];
		
		if(explode.length != this.dimension){
			System.out.println("Expected number of dimensions : " + this.dimension);
			System.out.println("Input key number of dimensions : " + explode.length);
			System.out.println("Input string : " + input);
			throw new IOException("Wrong number of dimensions in input key");
		}
		
		for(int i = 0; i<dimensions.length; i++){
			dimensions[i] = new StringDimension(explode[i]);
		}
		ArrayVertexID vertexID = new ArrayVertexID(dimensions,this.dimension);
			
		return vertexID;
		
	}

}
