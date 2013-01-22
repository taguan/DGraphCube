package io;


import java.io.IOException;

import graph.*;

/**
 * Parses a MultiDimensionnalVertexID from its String representation
 * 
 * @author Benoit Denis
 *
 */
public abstract class MultiDimVertexIDParser<T,S> implements VertexIDParser<MultiDimensionnalVertexID<T>,S> {

	/**
	 * MultiDimensionnalVertexID.setNbrOfDimensions must have be called before this.
	 * 
	 * @param input String of the form "dim1 dim2 dim3 ..."
	 */
	public abstract MultiDimensionnalVertexID<T> parseID(S input) throws IOException;
}
