package graphing;
import java.util.*;

public class WeightedGraph implements Iterable<Vertex>{
	
	private int howManyVertexes = 0;
	private int maxVertexes;
	private ArrayList<Vertex> graph;
	private ArrayList<String> vertexTracker = new ArrayList<String>(); 
	//pathWeight is used to report the weight of a path and is cleared before each search
	private int pathWeight = 0;		
	private Parser parser;

	public WeightedGraph(int maxVertexes, Parser parser) {
		this.parser = parser;
		this.maxVertexes = maxVertexes;
		graph = new ArrayList<Vertex>(maxVertexes);
	}

	public ArrayList<String> getVertexes(){
		return vertexTracker;
	}
	
	//this iterates thru vertexes only
	@Override
	public Iterator<Vertex> iterator() {
		return new MyIterator();
	}

	public class MyIterator implements Iterator<Vertex>{
		private int counter;
		
		@Override
		public boolean hasNext() {
			return counter < graph.size();
		}
		
		@Override
		public Vertex next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			else
				return graph.get(counter++);
		}
	
	}
	
	public boolean isEmpty() {
		return howManyVertexes == 0;
	}
	
	public boolean isFull() {
		return howManyVertexes == maxVertexes;
	}
	
	/*
	 * to add a vertex, we increment out vertex count which we check against 
	 * to make sure graph is not filled beyond capacity. we create a new vertex
	 * and insert it into the graph. we also insert the data into our vertexTracker
	 * which helps us get the proper indexes when searching the graph
	 */
	
	public void addVertex(Object vertexData) {
		if (isFull()) {
			System.out.println("Graph is full. Cannot add more vertexes.");
		}
		else {
		howManyVertexes++;
		Vertex vertex = new Vertex(vertexData);
		graph.add(vertex);
		vertexTracker.add(vertexData.toString());
		}
	}
	
	/*
	 * i wasn't able to figure out how to make a relationship bidirectional with nodes
	 * we get the index of the proper vertex, and check if it already has a relationship weight set
	 * if so, we set the 2nd degree relationship weight. if not, we set the 1st degree relationship
	 * weight. then we create a new node with the toVertexData (so data will not be overwritten)
	 * and append it to fromVertex's next null availability
	 */
	
	public void addEdge(Relationship relationship) {
		Object fromVertexData = relationship.getFromVertex();
		Object toVertexData = relationship.getToVertex();
		int weight = relationship.getHowClose();
		
	    int fromVertexIndex = vertexTracker.indexOf(fromVertexData.toString());
	    Vertex fromVertex = graph.get(fromVertexIndex);
	    
	    if (fromVertex.weightToFirst == 0)
	    	fromVertex.weightToFirst = weight;
	    else 
	    	fromVertex.weightToSecond = weight;
	    // Create a new edge vertex and set its weight
	    Vertex newEdge = new Vertex(toVertexData);

	    // Append the new edge to the end of the chain
	    newEdge.next = fromVertex.next;
	    fromVertex.next = newEdge;
	    
	}

	//get the given vertex at an index. this method is not used, but allows for
	//code re-usability and flexibility
	public String getVertexString(int index) {
		return graph.get(index).toString();
	}

	 // this complex toString method creates an adjacency table
	@Override 
	public String toString() {
	    StringBuilder table = new StringBuilder();
	    table.append("      |");
	    
	    // Print column headers
	    for (String vertex : vertexTracker) {
	        table.append(String.format("%-12s|", vertex));
	    }
	    table.append("\n");
	    
	    // Print separator line
	    for (int i = 0; i < vertexTracker.size(); i++) {
	        table.append("------|");
	        for (int k = 0; k < vertexTracker.size(); k++) {
	            table.append("------------|");
	        }
	        table.append("\n\n");
	        
	        // Print row data
	        table.append(String.format("%-6s|", vertexTracker.get(i)));
	        for (int j = 0; j < vertexTracker.size(); j++) {
	            if (i == j) {
	                table.append(String.format("%-12s|", "   *"));
	            } else {
	                int weight = findWeight(graph.get(i), graph.get(j));
	                String rel = parser.parse(weight);
	                table.append(String.format("%-12s|", rel));
	            }
	        }
	        table.append("\n\n");
	    }
	    
	    // Print final separator line
	    table.append("------|");
	    for (int k = 0; k < vertexTracker.size(); k++) {
	        table.append("------------|");
	    }
	    table.append("\n\n");
	    
	    return table.toString();
	}

	//we search for the matching relationship and return the appropriate weight
	
	private int findWeight(Vertex fromVertex, Vertex toVertex) {
		
		  if (fromVertex.next.data.equals(toVertex.data)) {
	            return fromVertex.weightToFirst;
	        }
		  else if (fromVertex.next.next != null && fromVertex.next.next.data.equals(toVertex.data)){
			  return fromVertex.weightToSecond;
		  }
		  else
			  return 0;
	}
	
	//overloaded method so that in can be used internally or externally (by Dijkstra)
	
	public int findWeight(Object fromVertexData, Object toVertexData) {
		
	    int fromVertexIndex = vertexTracker.indexOf(fromVertexData.toString());
	    Vertex fromVertex = graph.get(fromVertexIndex);
	    int toVertexIndex = vertexTracker.indexOf(toVertexData.toString());
	    Vertex toVertex = graph.get(toVertexIndex);
		
		  if (fromVertex.next.data.equals(toVertex.data)) {
	            return fromVertex.weightToFirst;
	        }
		  else if (fromVertex.next.next != null && fromVertex.next.next.data.equals(toVertex.data)){
			  return fromVertex.weightToSecond;
		  }
		  else
			  return 0;
	}


	
	public void markVertex(Object vertex) {
		int index = vertexTracker.indexOf(vertex);
		graph.get(index).found = true;
	}
	
	public boolean isMarked(Object vertex) {
		int index = vertexTracker.indexOf(vertex);
		return graph.get(index).found;
	}
	
	public void clearMarks() {
		for (Vertex vertex: graph) {
			vertex.found = false;
		}
	}
	
	//this method was copied from the book
    
    public PriorityQueue<Object> getToVertices(Object vertexData) {
    	PriorityQueue<Object> queue = new PriorityQueue<Object>();
    	int index = vertexTracker.indexOf(vertexData);
    	Vertex vertex = graph.get(index);
    	System.out.println(vertex.data + " has a " + parser.parse(vertex.weightToFirst) +
    			" relationship with " + vertex.next.data + ".");

		pathWeight += vertex.weightToFirst;
    	queue.add(vertex.next.data);
    	return queue;	
    }
   
	//this method was copied from the book
    
	public boolean breadthFirstSearch(Object startVertex, Object endVertex) {
		
		System.out.println("We trace backwards from " + startVertex + " to " + endVertex + ".");
		PriorityQueue<Object> queue = new PriorityQueue<Object>();
		PriorityQueue<Object> vertexQueue = new PriorityQueue<Object>();
		
		boolean found = false;
		Object vertex;
		Object item;
		
		clearMarks();
		pathWeight = 0;
		queue.add(startVertex);
		do
		{
			vertex = queue.poll();
			if (vertex == endVertex)
				found = true;
			else
			{
				if (!isMarked(vertex));
				{
					markVertex(vertex);
					vertexQueue = getToVertices(vertex);
					
					while (!vertexQueue.isEmpty())
					{
						item = vertexQueue.poll();
						if (!isMarked(item)) {
							queue.add(item);
						}
					}
				}
				
			}
		} while (!queue.isEmpty() && !found);
		
		return found;
	}
	
	public int getPathWeight() {
		return pathWeight;
	}
	
	
}
