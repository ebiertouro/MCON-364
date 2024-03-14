package graphing;

public interface WeightedGraphInterface<T> {
	
	public boolean isEmpty();
	
	public boolean isFull();
	
	public void addVertex(Object vertex);
	
	public void addEdge(Object fromVertex, Object toVertex, int weight);
	
	public int weightIs(Object fromVertex, Object toVertex);
	
	//public QueueInterface getToVertices(Object vertex);
}
