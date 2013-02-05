package materialization;

import java.util.LinkedList;

import org.apache.hadoop.fs.Path;

import cuboid.BaseAggregate;
import cuboid.CuboidEntry;

/**
 * Implementation of the min level strategy described in Graph Cube paper
 * @author Benoît Denis
 *
 */
public class MinLevelStrategy implements MaterializationStrategy {
	

	private int maxCuboids;
	
	private int count;
	private int nbrOfDimensions;
	private CuboidEntry next;
	private int level;
	private LinkedList<String> combinations;
	private Path basePath;
	
	private GraphKeeper graphCube;
	
	public MinLevelStrategy(int minLevel, int maxCuboids, int nbrOfDimensions, Path inputPath){
		this.level = nbrOfDimensions - minLevel;
		this.nbrOfDimensions = nbrOfDimensions;
		this.maxCuboids = maxCuboids;
		this.next = null;
		this.count = 0;
		this.graphCube = new SortedListKeeper(nbrOfDimensions,inputPath,minLevel);
		this.combinations = new LinkedList<String>();
		this.basePath = inputPath;
	}
	
	public boolean finished(CuboidEntry lastComputed){
		
		if(lastComputed != null){
			//add the lastly computed cuboid to the cube and increment the count
			graphCube.addCuboid(lastComputed);
			this.count++;
		}
		
		if(this.count == this.maxCuboids) { //we materialized the number  of cuboid asked
			return true; 
		}
		
		//we determine the next cuboid to be computed and memorize it
		BaseAggregate fun = new BaseAggregate();
		String funString = computeNextFunction();
		fun.parseFunction(funString);
		this.next = new CuboidEntry(fun,-1,
				this.basePath.suffix(funString));
		
		return funString.equals("");
		//if base cuboid, we stop
					
	}
	
	private String computeNextFunction(){
		
		if(combinations.isEmpty()){
			if(this.level == 0){ //base cuboid
				return "";
			}
			
			//generate next level of combinations
			combinations = CombinationsGenerator.comb(this.level, nbrOfDimensions);
			this.level--;
		}
		return combinations.pop();
	}


	public CuboidEntry[] nextAggregate(){
		CuboidEntry[] toReturn = new CuboidEntry[2];
		//toReturn[0] = graphCube.getNearestDescendant(this.next.getAggregateFunction());
		toReturn[0] = graphCube.getBaseCuboid(); //property of Min level Strategy
		toReturn[1] = this.next;
		
		return toReturn;
	}

}
