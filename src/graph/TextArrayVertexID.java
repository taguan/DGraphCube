package graph;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;

public class TextArrayVertexID implements MultiDimensionnalVertexID<String> {
	
	public int nbrOfDimensions;
	private String[]dimensions;
	
	public TextArrayVertexID(int nbrOfDimensions){
		this.nbrOfDimensions = nbrOfDimensions;
		this.dimensions = null;
	}
	
	public TextArrayVertexID(String[]dimensions, int nbrOfDimensions){
		this(nbrOfDimensions);
		this.dimensions = dimensions;
	}
	
	public void setNbrOfDimensions(int nbrOfDimensions){
		this.nbrOfDimensions = nbrOfDimensions;
	}
	
	public int getNbrOfDimensions(){
		return nbrOfDimensions;
	}

	
	public String getDimension(int index){
		return dimensions[index];
	}
	
	public String toString(){
		StringBuffer strb = new StringBuffer();
		for(int i = 0; i<dimensions.length-1; i++){
			strb.append(dimensions[i]);
			strb.append(" ");
		}
		strb.append(dimensions[dimensions.length-1]);
		
		return strb.toString();
	}

}
