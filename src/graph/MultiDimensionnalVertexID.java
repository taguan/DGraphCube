package graph;

import java.io.*;

import org.apache.hadoop.io.*;

/**
 * Representation for multidimensional vertex identifiers
 * Must extends WritableComparable
 * 
 * @author Benoit Denis
 *
 */

public interface MultiDimensionnalVertexID 
	extends WritableComparable<MultiDimensionnalVertexID> {
	
	public void write(DataOutput out) throws IOException;
	
	public void readFields(DataInput in) throws IOException;
	
	public int compareTo(MultiDimensionnalVertexID other);
}
