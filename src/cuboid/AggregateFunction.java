package cuboid;

import graph.*;

/**
 * 
 * @author Benoit Denis
 *
 * Defines an aggregate function to aggregate vertices and edges
 *
 */
public interface AggregateFunction {

	/**
	 * Aggregate a vertex, modifies the dimensions to be aggregated in vertexID 
	 * @param vertexID vertex to be aggregated
	 */
	public void aggregateVertex(MultiDimensionnalVertexID vertexID);
	
	
	/**
	 * Initialize the internal data structure representing the aggregate function
	 * 
	 * @param input String representation of the aggregate function. Form int,int,...
	 *         For instance 0,2 means that dimensions 0 and 2 have to be aggregated
	 */
	public void parseFunction(String input);
	
	/**
	 * Return true if this function is a descendant of parameter function
	 * Descendant means that for every aggregated dimension of this, the dimension
	 * is aggregated in the input function
	 * 
	 * @param inputFunction Function to be compared
	 */
	public boolean isDescendant(AggregateFunction inputFunction);
	
	/**
	 * Return true if dimension i is aggregated, false otherwise
	 * 
	 * @param i Index of dimension
	 */
	public boolean isAggregated(int i);
	
	/**
	 * 
	 * @return an array of the index of dimensions to be aggregated by this function
	 */
	public int[] getToAggregate();
}
