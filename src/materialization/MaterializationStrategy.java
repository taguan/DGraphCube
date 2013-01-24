package materialization;

/**
 * Interface for all materialization strategies
 * @author Benoit Denis
 *
 */
public interface MaterializationStrategy {

	/**
	 * Tell whether the materialization is finished
	 * @return true if finished, false otherwise
	 */
	public boolean finished();
	
	/**
	 * Gives the aggregate function to compute the next
	 * aggregated network
	 * @return String representation of an aggregate function
	 *          of the form dim1,dim2,...  for instance "0,2" 
	 *          will aggregate dimensions 0 and 2.
	 */
	public String nextAggregate();
}
