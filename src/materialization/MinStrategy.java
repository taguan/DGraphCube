package materialization;

/**
 * Dumb and incorrect implementation, just for test purpose at the moment
 * @author benoit
 *
 */
public class MinStrategy implements MaterializationStrategy {
	
	private final int minLevel;
	private int level;
	private final int nbrOfDimensions;
	
	public MinStrategy(int minLevel, int nbrOfDimensions){
		this.minLevel = minLevel;
		this.nbrOfDimensions = nbrOfDimensions;
		this.level = 0;
	}
	
	public boolean finished(){
		return level == minLevel;
	}
	
	public String nextAggregate(){
		level++;
		return new Integer(level%nbrOfDimensions).toString();
	}

}
