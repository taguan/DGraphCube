package materialization;

import java.util.ArrayList;

import org.apache.hadoop.fs.Path;

import cuboid.AggregateFunction;
import cuboid.BaseAggregate;
import cuboid.CuboidEntry;


/**
 * Graph keeper implemented by ArrayList (for each level) of ArrayList (for each cuboid)
 * It assumes a minLevel algorithm to be used
 * @author Benoit Denis
 *
 */

public class SortedListKeeper implements GraphKeeper {

	private Path basePath;
	private int dimensions;
	private ArrayList<ArrayList<CuboidEntry>> graphCube;
	private int minLevel;
	
	
	public SortedListKeeper(int dimensions, Path pathToRoot, Path inputPath, int minLevel){
		this.basePath = pathToRoot;
		this.dimensions = dimensions;
		this.graphCube = new ArrayList<ArrayList<CuboidEntry>>(dimensions+1);
		this.minLevel = minLevel;
		
		for(int i = 0; i <= this.dimensions; i++){
			this.graphCube.add(new ArrayList<CuboidEntry>());
		}
		
		
		//add the base cuboid
		AggregateFunction baseCuboid = new BaseAggregate();
		baseCuboid.parseFunction("");
		CuboidEntry toAdd = new CuboidEntry(baseCuboid,Long.MAX_VALUE,inputPath);
		this.graphCube.get(dimensions).add(toAdd);
	}
	
	/**
	 * Add the cuboid at the level in the graphCube corresponding to the level parameter.
	 * In a same level, the list is sorted in growing order (depending of the size).
	 * This cannot be the base cuboid.
	 */
	@Override
	public void addCuboid(AggregateFunction aggregateFunction, long size) {
		
		Path newPath = this.basePath.suffix(aggregateFunction.toString());
		CuboidEntry toAdd = new CuboidEntry(aggregateFunction, size, newPath);
		
		ArrayList<CuboidEntry> currentLevel = graphCube.get(getLevel(aggregateFunction));
		if(currentLevel.size() == 0) {
			currentLevel.add(toAdd);
			return;
		}
		
		int i = 0;
		CuboidEntry current = currentLevel.get(i);
		
		while(current.getSize() < size){
			i++;
			if(i == currentLevel.size()){
				currentLevel.add(toAdd);
				return;
			}
			current = currentLevel.get(i);
		}
		currentLevel.add(i,toAdd);

	}

	/**
	 * This is implemented with MinLevelStrategy in mind.
	 * We start searching in level + 1. We stop immediately 
	 */
	@Override
	public CuboidEntry getNearestDescendant(AggregateFunction aggregateFunction) {
		
		int level = this.getLevel(aggregateFunction);
		
		if(level == dimensions){ // base cuboid
			return graphCube.get(level).get(0);
		}
		
		level++; //closest descendant level
		while(true){

			ArrayList<CuboidEntry> directDescendantLevel = graphCube.get(level);
		
			for(CuboidEntry cuboid : directDescendantLevel){
				if(cuboid.getAggregateFunction().isDescendant(aggregateFunction)){
					return cuboid;
				}
			}
			level++;
			if(level > this.minLevel) break;
		}
		
		//property of MinLevel, if no descendant found in the minLevel descendants, then the nearest
		//descendant must be the base cuboid
		return graphCube.get(dimensions).get(0);
		
	}
	
	public int getLevel(AggregateFunction func){
		return this.dimensions - func.getToAggregate().length;
	}
	

}
