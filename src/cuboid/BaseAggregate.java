package cuboid;


import graph.*;

/**
 * 
 * @author Benoît Denis
 * 
 * Base implementation for an aggregate function.
 *
 */
public class BaseAggregate implements AggregateFunction {

	int[] dimToAggregate;
	
	public BaseAggregate(){
		dimToAggregate = null;
	}
	
	public void aggregateVertex(MultiDimensionnalVertexID vertexID){
		for (int i : dimToAggregate){
			vertexID.setDimension(i, vertexID.getDimension(i).getAggregate() );
		}
	}
	
	public void parseFunction(String input){
		String[]explode = input.split(",");
		dimToAggregate = new int[explode.length];
		for(int i = 0; i < explode.length; i++){
			dimToAggregate[i] = Integer.parseInt(explode[i]);
		}
	}

}
