package graph;



/**
 * Representation for multidimensional vertex identifiers
 * 
 * @author Benoit Denis
 *
 */

public interface MultiDimensionnalVertexID  {
	
	
	/**
	 * Gets the index th dimension
	 * @param index the index of the dimension to be returned
	 * @return the corresponding dimension
	 */
	public Dimension getDimension(int index);
	
	/**
	 * Changes the value of the dimension of index 'index"
	 * @param index
	 * @param newValue
	 */
	public void setDimension(int index, Dimension newValue);
	
	/**
	 * Set the number of dimensions.
	 * Should be called once, at the beginning
	 * @param nbrOfDimensions
	 */
	public void setNbrOfDimensions(int nbrOfDimensions);
	
	public int getNbrOfDimensions();
	
	public String toString(String delimiter);
}
