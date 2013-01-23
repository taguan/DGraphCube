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
}
