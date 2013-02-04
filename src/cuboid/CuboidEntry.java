package cuboid;

import org.apache.hadoop.fs.Path;

public class CuboidEntry {

	private AggregateFunction aggregateFunction;
	private long size;
	private Path path;
	
	public CuboidEntry(AggregateFunction aggregateFunction, long size, Path path){
		this.aggregateFunction = aggregateFunction;
		this.size = size;
		this.path = path;
	}
	
	public AggregateFunction getAggregateFunction(){
		return this.aggregateFunction;
	}
	
	public long getSize(){
		return this.size;
	}
	
	public Path getPath(){
		return this.path;
	}
	
	public boolean equals(Object other){
		if(!(other instanceof CuboidEntry)){
			return false;
		}
		CuboidEntry otherEntry = (CuboidEntry) other;
		
		return (this.getAggregateFunction().equals(otherEntry.getAggregateFunction())
				&&
				this.getPath().equals(otherEntry.getPath())
				&&
				(this.getSize() == otherEntry.getSize()));
	}
}
