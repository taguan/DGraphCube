package graph;



/**
 * Representation for multidimensional vertex identifiers
 * 
 * @author Benoit Denis
 * @param <T> Type representing dimensions
 *
 */

public interface MultiDimensionnalVertexID<T>  {
	
	
	/**
	 * Gets the index th dimension
	 * @param index the index of the dimension to be returned
	 * @return the corresponding dimension
	 */
	public T getDimension(int index);
	
	/**
	 * Changes the value of the dimension of index 'index"
	 * @param index
	 * @param newValue
	 */
	public void setDimension(int index, T newValue);
	
	/**
	 * Set the number of dimensions.
	 * Should be called once, at the beginning
	 * @param nbrOfDimensions
	 */
	public void setNbrOfDimensions(int nbrOfDimensions);
	
	public int getNbrOfDimensions();
}
