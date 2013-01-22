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

	public abstract MultiDimensionnalVertexID<T> parseID(S input) throws IOException;
}
