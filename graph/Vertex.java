package graphing;

public class Vertex  implements Comparable<Vertex>{
	
	//each vertex stores data, a pointer (which can link indefinitely) and the distance to up to 2 nodes
	//it also includes boolean found to make the clear/mark vertex methods very simple
	
	Object data;
	Vertex next;
	int weightToFirst;
	int weightToSecond;
	boolean found;

	public Vertex(Object data) {
		this.data = data;
		next = null;
		found = false;
		weightToFirst = 0;
		weightToSecond = 0;
}
	
	public String toString() {
	    return data.toString();
	}

	
	@Override
	public int compareTo(Vertex other) {
		return this.weightToFirst - other.weightToFirst;
	}

}
