package graph;

import java.io.*;

import org.apache.hadoop.io.*;

/**
 * Representation for multidimensional vertex identifiers
 * Must extends WritableComparable
 * 
 * @author Benoit Denis
 * @param <T> Type representing dimensions
 *
 */

public interface MultiDimensionnalVertexID<T> 
	extends WritableComparable<MultiDimensionnalVertexID<T>> {
	
	public void write(DataOutput out) throws IOException;
	
	public void readFields(DataInput in) throws IOException;
	
	public int compareTo(MultiDimensionnalVertexID<T> other);
	
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
