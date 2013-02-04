package materialization;


import cuboid.AggregateFunction;
import cuboid.CuboidEntry;


/**
 * Responsible for tracking materialized graphs
 * and for finding the nearest ancestor of an 
 * aggregate
 * @author Benoit Denis
 *
 */
public interface GraphKeeper {

	/**
	 * Ask the GraphKeeper to remember the location of a new graph
	 * This cannot be the base cuboid. It is automatically added at initialization
	 * 
	 * @param aggregateFunction  aggregateFunction of the cuboid, used as identifier
	 * @param size  number of vertices and edges of the cuboid
	 */
	public void addCuboid(AggregateFunction aggregateFunction,  long size);
	
	/**
	 * 
	 * @param aggregateFunction of the input cuboid
	 * @return path and aggregate function of the nearest descendant
	 */
	public CuboidEntry getNearestDescendant(AggregateFunction aggregateFunction);
}
