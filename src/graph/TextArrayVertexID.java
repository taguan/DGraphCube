package graph;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;

public class TextArrayVertexID implements MultiDimensionnalVertexID<String> {
	
	private static int nbrOfDimensions = 0;
	private String[]dimensions;
	
	public TextArrayVertexID(){
		this.dimensions = null;
	}
	
	public TextArrayVertexID(String[]dimensions){
		this.dimensions = dimensions;
	}
	
	public void setNbrOfDimensions(int nbrOfDimensions){
		TextArrayVertexID.nbrOfDimensions = nbrOfDimensions;
	}
	
	public int getNbrOfDimensions(){
		return nbrOfDimensions;
	}
	
	public void write(DataOutput out) throws IOException{
		for(String dimension : dimensions){
			Text text = new Text(dimension);
			text.write(out);
		}
	}
	
	public void readFields(DataInput in) throws IOException{
		this.dimensions = new String[nbrOfDimensions];
		for(int i = 0; i < nbrOfDimensions; i++){
			Text text = new Text();
			text.readFields(in);
			this.dimensions[i] = text.toString();
		}
	}
	
	/**
	 * Compare the first string, if equals, the second, etc.
	 */
	public int compareTo(MultiDimensionnalVertexID<String> other){
		for(int i = 0; i < nbrOfDimensions; i++){
			int res = this.dimensions[i].compareTo(other.getDimension(i));
			if(res != 0) return res;
		}
		return 0;
	}
	
	public String getDimension(int index){
		return dimensions[index];
	}

}
