package io;

import java.io.IOException;

/**
 * Parsing VertexID from input keys
 * 
 * @author Benoit Denis
 *
 * @param <K> VertexID representation
 * @param <P> Input format
 */

public interface VertexIDParser<K,P> {
	
	/**
	 * Parses a vertex ID
	 * 
	 * @param input Input to be parsed
	 * @return A representation of the VertexID
	 * 
	 **/
	public K parseID(P input) throws IOException;

}
