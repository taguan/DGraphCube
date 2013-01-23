package io;

import graph.*;

import java.io.IOException;
import java.util.ArrayList;

public class StringValueParser implements ValueParser<String> {

	private boolean checkActivated = false;
	private int dimension;
	private MultiDimensionnalVertexID sourceVertex;
	private String edgeSeparator, vertexSeparator;
	
	public StringValueParser(int dimension, MultiDimensionnalVertexID sourceVertex,
			String edgeSeparator, String vertexSeparator){
		this.dimension = dimension;
		this.sourceVertex = sourceVertex;
		this.edgeSeparator = edgeSeparator;
		this.vertexSeparator = vertexSeparator;
	}
	
	/**
	 * Key has the form : vertexWeight sep destVertexID sep edgeWeight sep ...
	 */
	public ArrayList<Object> parseValue(String input) throws IOException{
		String[]explode = input.split(edgeSeparator);
		
		ArrayList<Object> toReturn = new ArrayList<Object>(explode.length/2 + 1);
		Long vertexWeight = Long.parseLong(explode[0]);
		toReturn.add(vertexWeight);
		
		StringToStringArrayVertexIDParser vertexParser = new StringToStringArrayVertexIDParser(dimension);
		
		for(int i = 1; i<explode.length; i = i + 2){
			MultiDimensionnalVertexID destVertex = vertexParser.parseID(explode[i], vertexSeparator);
			long weight = Long.parseLong(explode[i+1]);
			DirectedEdge edge = new DirectedEdge(sourceVertex,destVertex,weight);
			if(checkActivated){
				if(!edge.isOutgoing()) continue;
			}
			toReturn.add(edge);
		}
		
		return toReturn;
	}
	

	public void checkEdges(boolean activate){
		this.checkActivated = activate;
	}
}
