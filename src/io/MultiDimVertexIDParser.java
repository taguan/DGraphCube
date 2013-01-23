package io;


import java.io.IOException;

import graph.*;

/**
 * Parses a MultiDimensionnalVertexID from its String representation
 * 
 * @author Benoit Denis
 * 
 * @param <T> Type of the dimension
 * @param <S> Type of the input
 *
 */
public abstract class MultiDimVertexIDParser<T,S> implements VertexIDParser<MultiDimensionnalVertexID,S> {

	public abstract MultiDimensionnalVertexID parseID(S input, S separator) throws IOException;
}
