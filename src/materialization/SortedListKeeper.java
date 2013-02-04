package materialization;

import java.util.ArrayList;

import org.apache.hadoop.fs.Path;

import cuboid.AggregateFunction;
import cuboid.CuboidEntry;


/**
 * Graph keeper implemented by a kind of binary tree
 * @author Benoit Denis
 *
 */

public class SortedListKeeper implements GraphKeeper {

	private Path basePath;
	private int dimensions;
	private ArrayList<ArrayList<CuboidEntry>> graphCube;
	
	
	public SortedListKeeper(int dimensions, Path pathToRoot){
		this.basePath = pathToRoot;
		this.dimensions = dimensions;
		this.graphCube = new ArrayList<ArrayList<CuboidEntry>>(dimensions);
		
		for(int i = 0; i < this.dimensions; i++){
			this.graphCube.add(new ArrayList<CuboidEntry>());
		}
	}
	
	/**
	 * Add the cuboid at the level in the graphCube corresponding to the level parameter.
	 * In a same level, the list is sorted in growing order (depending of the size).
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
		
		while(current.getSize() > size){
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
		
		if(level == dimensions - 1){ // base cuboid
			return graphCube.get(level).get(0);
		}
		
		ArrayList<CuboidEntry> directDescendantLevel = graphCube.get(level + 1);
		
		for(CuboidEntry cuboid : directDescendantLevel){
			if(cuboid.getAggregateFunction().isDescendant(aggregateFunction)){
				return cuboid;
			}
		}
		
		//property of MinLevel, if no descendant found in the direct descendant, then the nearest
		//descendant must be the base cuboid
		return graphCube.get(dimensions - 1).get(0);
		
	}
	
	public int getLevel(AggregateFunction func){
		return this.dimensions - func.getToAggregate().length;
	}
	

}
