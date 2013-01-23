package io;

import graph.MultiDimensionnalVertexID;

import java.io.IOException;

/**
 * Parsing VertexID and EdgeID from input keys
 * 
 * @author Benoit Denis
 *
 * @param <P> Input format
 */

public interface KeyParser<P> {
	
	/**
	 * Parses a vertex or an edge ID
	 * 
	 * @param input Input to be parsed
	 * @param vertexSeparator Separator to be used to split the dimensions of the vertex
	 * @param edgeSeparator  Separator to be used to split the two vertices of an edge
	 * @return A representation of the VertexID or the EdgeID (length 1 for Vertex, 2 for Edges)
	 * 
	 **/
	public MultiDimensionnalVertexID[]
			parseID(P input, P vertexSeparator, P edgeSeparator) throws IOException;

}
