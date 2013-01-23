package graph;

public class DirectedEdge {
	
	private MultiDimensionnalVertexID source;
	private MultiDimensionnalVertexID dest;
	private long weight;
	
	public DirectedEdge(MultiDimensionnalVertexID source, MultiDimensionnalVertexID dest, long weight){
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}
	
	public MultiDimensionnalVertexID getSource(){
		return this.source;
	}
	
	public MultiDimensionnalVertexID getDest(){
		return this.dest;
	}
	
	public long getWeight(){
		return weight;
	}
	
	public boolean isOutgoing(){
		return (this.source.toString().compareTo(this.dest.toString()) < 0);
	}
	
	public String toString(){
		return getSource().toString() + getDest().toString();
	}
}
