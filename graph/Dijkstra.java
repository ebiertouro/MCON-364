package graphing;
import java.util.*;

public class Dijkstra {
	//-------------------------------------------------------------------------------
	// shortestPaths.java by Dale/Joyce/Weems Chapter 9 p.608


	//WeightedGraph p593 Interface p590
	public void shortestPaths(WeightedGraph graph, String startVertex) {
	   // Writes the shortest distance from startVertex to every
	   // other reachable vertex in graph.


	   // Code for relationship is linked here
	   Relationship relationship; 
	   Relationship saveRel; // for saving on priority queue
	   int minDistance;
	   int newDistance;


	   //This is what allows the code to figure out what path is the smallest
	   // all comparisons take place in the creation of the PriQueueInterface
	   // HeapPriQ p.567   PriQueueInterface p.554
	   PriorityQueue<Relationship> pq = new PriorityQueue<Relationship>();
	   Object vertex;


	   //All vertices that are adjacent to our starting vertex
	   // QueueInterface p.222  LinkedQueue p.237
	   PriorityQueue<Object> vertexQueue = new PriorityQueue<Object>();


	   //We have not traveled anywhere, map should be cleared
	   graph.clearMarks();


	   // Our first relationship is from our startVertex to (itself) our startVertex with a distance of 0
	   saveRel = new Relationship(startVertex, startVertex, 0);


	   // Save in the priority Queue
	   pq.add(saveRel);


	   System.out.println("Last Vertex Destination Distance");
	   System.out.println("------------------------------------");


	   // This is a DO WHILE !!!!
	   do {
	       // FIFO structure, poll first relationship in queue
	       relationship = pq.poll();


	       // if our toVertex of relationship object is not already marked on the graph
	       if (!graph.isMarked(relationship.getToVertex())) {
	           // mark the toVertex so it cannot be traveled to again
	           graph.markVertex(relationship.getToVertex());


	           // print out relationship 
	           System.out.println(relationship);


	           // The toVertex becomes the fromVertex this is how we travel through, it is an iterator of sorts
	           relationship.setFromVertex(relationship.getToVertex());
	           // Get from and to distance
	           minDistance = relationship.getHowClose();
	           // get all adjacent vertices of our fromVertex and put in vertexQueue
	           vertexQueue = graph.getToVertices(relationship.getFromVertex());


	           // walk through adjacency queue as long as there are vertices
	           while (!vertexQueue.isEmpty()) {
	               // poll one vertex
	               vertex = vertexQueue.poll();
	               // if the vertex is not marked 
	               if (!graph.isMarked(vertex)) {
	                   // calculate the distance which is its distance PLUS prior distance
	                   newDistance = minDistance + graph.findWeight(relationship.getFromVertex(), vertex);


	                   // save a new relationship object
	                   saveRel = new Relationship(relationship.getFromVertex(), vertex.toString(), newDistance);
	                   // add saved relationship - however this is a priority queue,
	                   // so it is placed in the correct position
	                   pq.add(saveRel);
	               }
	           }
	       }
	   } while (!pq.isEmpty()); // do while there are relationships in the priority queue
	   System.out.println();
	}

}
