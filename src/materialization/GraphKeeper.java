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
	 * @param newCuboid Cuboid to be added
	 */
	public void addCuboid(CuboidEntry newCuboid);
	
	/**
	 * 
	 * @param aggregateFunction of the input cuboid
	 * @return path and aggregate function of the nearest descendant
	 */
	public CuboidEntry getNearestDescendant(AggregateFunction aggregateFunction);
	
	/**
	 * 
	 * @return The base cuboid
	 */
	public CuboidEntry getBaseCuboid();
	

}
