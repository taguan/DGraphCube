package materialization;

import org.apache.hadoop.fs.Path;

import cuboid.CuboidEntry;

/**
 * Dumb and incorrect implementation, just for test purpose at the moment
 * @author Benoît Denis
 *
 */
public class MinStrategy implements MaterializationStrategy {
	

	private final int maxCuboids;
	
	private final int minLevel;
	private int count;
	private final int nbrOfDimensions;
	private CuboidEntry next;
	
	private GraphKeeper graphCube;
	
	public MinStrategy(int minLevel, int maxCuboids, int nbrOfDimensions, Path inputPath){
		this.minLevel = minLevel;
		this.nbrOfDimensions = nbrOfDimensions;
		this.maxCuboids = maxCuboids;
		this.next = null;
		this.count = 0;
		graphCube = new SortedListKeeper(nbrOfDimensions,inputPath,inputPath,minLevel);
	}
	
	public boolean finished(CuboidEntry lastComputed){
		
		graphCube.addCuboid(lastComputed.getAggregateFunction(), lastComputed.getSize());
		this.count++;
		this.next = computeNextCuboid(lastComputed);
		
		return (this.count == this.maxCuboids || this.next.getAggregateFunction().toString() == ""); 
					
	}
	
	private CuboidEntry computeNextCuboid(CuboidEntry previous) {
		int[] dimToAggregate;
		
		if(previous == null){ //first time it is called
			dimToAggregate = new int[this.nbrOfDimensions - this.minLevel];
			for(int i = 0; i < dimToAggregate.length; i++){
				dimToAggregate[i] = i;
			}
		}
		
		
	}

	public CuboidEntry[] nextAggregate(){
		CuboidEntry[] toReturn = new CuboidEntry[2];
		toReturn[0] = graphCube.getNearestDescendant(this.next.getAggregateFunction());
		toReturn[1] = this.next;
		
		return toReturn;
	}

}
