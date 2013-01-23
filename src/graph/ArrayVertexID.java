package graph;


public class ArrayVertexID<T> implements MultiDimensionnalVertexID<T> {
	
	public int nbrOfDimensions;
	private T[]dimensions;
	
	public ArrayVertexID(int nbrOfDimensions){
		this.nbrOfDimensions = nbrOfDimensions;
		this.dimensions = null;
	}
	
	public ArrayVertexID(T[]dimensions, int nbrOfDimensions){
		this(nbrOfDimensions);
		this.dimensions = dimensions;
	}
	
	public void setNbrOfDimensions(int nbrOfDimensions){
		this.nbrOfDimensions = nbrOfDimensions;
	}
	
	public int getNbrOfDimensions(){
		return nbrOfDimensions;
	}

	
	public T getDimension(int index){
		return dimensions[index];
	}
	
	public void setDimension(int index, T newValue){
		dimensions[index] = newValue;
	}
	
	public String toString(){
		StringBuffer strb = new StringBuffer();
		for(int i = 0; i<dimensions.length-1; i++){
			strb.append(dimensions[i].toString());
			strb.append(" ");
		}
		strb.append(dimensions[dimensions.length-1].toString());
		
		return strb.toString();
	}

}
