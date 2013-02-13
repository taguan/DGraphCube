package materialization;

import cuboid.CuboidEntry;

/**
 * Interface for all materialization strategies
 * @author Benoit Denis
 *
 */
public interface MaterializationStrategy {

	/**
	 * Tell whether the materialization is finished. It alos adds the lastComputed CuboidEntry
	 * 
	 * @param lastComputed Informs the strategy about the last computed cuboid
	 * 
	 * @return true if finished, false otherwise
	 */
	public boolean finished(CuboidEntry lastComputed);
	
	/**
	 * Gives the aggregate function to compute the next
	 * aggregated network
	 * @return CuboidEntry representation of an aggregate function
	 *          of the form dim1,dim2,...  for instance "0,2" 
	 *          will aggregate dimensions 0 and 2
	 *          + path + size = -1 (to be updated before being added with finished)
	 *          index 0 = cuboid from which to compute the next cuboid to materialize (index 1)
	 */
	public CuboidEntry[] nextAggregate();
	
	/**
	 * Gets the graph keeper object
	 */
	public GraphKeeper getGraphKeeper();
}
