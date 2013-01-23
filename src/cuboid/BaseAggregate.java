package cuboid;


import graph.*;


public abstract class BaseAggregate<T> implements AggregateFunction<T> {

	int[] dimToAggregate;
	
	public BaseAggregate(){
		dimToAggregate = null;
	}
	
	public void aggregateVertex(MultiDimensionnalVertexID<T> vertexID){
		for (int i : dimToAggregate){
			vertexID.setDimension(i, aggregateDimension());
		}
	}
	
	public void parseFunction(String input){
		String[]explode = input.split(",");
		dimToAggregate = new int[explode.length];
		for(int i = 0; i < explode.length; i++){
			dimToAggregate[i] = Integer.parseInt(explode[i]);
		}
	}
	
	public abstract T aggregateDimension();
}
