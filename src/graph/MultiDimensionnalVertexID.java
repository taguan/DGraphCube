package graph;


import org.apache.hadoop.io.*;

/**
 * Representation for multidimensional vertex identifiers
 * 
 * @author Benoit Denis
 * @param <T> Type representing dimensions
 *
 */

public interface MultiDimensionnalVertexID<T> 
	extends WritableComparable<MultiDimensionnalVertexID<T>> {
	
	
	/**
	 * Gets the index th dimension
	 * @param index the index of the dimension to be returned
	 * @return the corresponding dimension
	 */
	public T getDimension(int index);
	
	/**
	 * Set the number of dimensions.
	 * Should be called once, at the beginning
	 * @param nbrOfDimensions
	 */
	public void setNbrOfDimensions(int nbrOfDimensions);
	
	public int getNbrOfDimensions();
}
