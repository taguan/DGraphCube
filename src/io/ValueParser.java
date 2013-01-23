package io;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Benoit Denis
 * 
 * Interface used by the Mapper to parse its keys
 * 
 * @param <I> Input type
 */
public interface ValueParser<I> {

	/**
	 * Parse a key. The key must contain the following information :
	 * Vertex weight
	 * Outgoing edges (such that distant vertex > current vertex)
	 * If there are ingoing edges, they are just ignored (ensured only if check enabled)
	 * 
	 * @param input
	 * @return First object is an Integer, followings are outgoing Edges
	 */
	public ArrayList<Object> parseValue(I input) throws IOException;
	
	/**
	 * If activated, check if the edges are outgoing and do not recored ingoing 
	 * edges. Per default, it is not activated.
	 * 
	 * @param activate True to check if the edges are outgoing and to ignore
	 *                  possible ingoing edges
	 */
	public void checkEdges(boolean activate);
}
