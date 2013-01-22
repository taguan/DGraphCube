package io;

import java.io.IOException;
import graph.*;

public class StringToTextArrayVertexIDParser extends MultiDimVertexIDParser<String,String> {

	public MultiDimensionnalVertexID<String> parseID(String input) throws IOException{
		String[]explode = input.split(" ");
		TextArrayVertexID vertexID = new TextArrayVertexID(explode);
		
		if(explode.length != vertexID.getNbrOfDimensions()){
			throw new IOException("Wrong number of dimensions in input key");
		}
		
		return vertexID;
		
	}
}
