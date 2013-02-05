package cuboid;


import java.util.Arrays;

import graph.*;

/**
 * @author Benoît Denis
 * 
 * Base implementation for an aggregate function.
 *
 */
public class BaseAggregate implements AggregateFunction {

	private int[] dimToAggregate;
	
	public BaseAggregate(){
		dimToAggregate = null;
	}
	
	public void aggregateVertex(MultiDimensionnalVertexID vertexID){
		for (int i : dimToAggregate){
			vertexID.setDimension(i, vertexID.getDimension(i).getAggregate() );
		}
	}
	
	public void parseFunction(String input){
		if(input.equals("")){
			dimToAggregate = new int[0];
			return;
		}
		String[]explode = input.split(",");
		dimToAggregate = new int[explode.length];
		for(int i = 0; i < explode.length; i++){
			dimToAggregate[i] = Integer.parseInt(explode[i]);
		}
	}
	
	public boolean isDescendant(AggregateFunction inputFunction){
		for(int i : this.dimToAggregate){
			if(!inputFunction.isAggregated(i)) return false;
		}
		return true;
	}
	
	public int[] getToAggregate(){
		return this.dimToAggregate;
	}
	
	public boolean isAggregated(int i){
		return Arrays.binarySearch(dimToAggregate,i) >= 0;
	}
	
	public String toString(){
		if(this.dimToAggregate.length == 0) return "";
		StringBuffer strb = new StringBuffer();
		for(int i = 0; i < this.dimToAggregate.length - 1; i++){
			strb.append(this.dimToAggregate[i]);
			strb.append(",");
		}
		strb.append(this.dimToAggregate[this.dimToAggregate.length -1]);
		
		return strb.toString();
	}
	
	public boolean equals(Object other){
		if(!(other instanceof BaseAggregate)) return false;
		BaseAggregate otherFun = (BaseAggregate) other;
		return otherFun.toString().equals(this.toString());
	}

}
