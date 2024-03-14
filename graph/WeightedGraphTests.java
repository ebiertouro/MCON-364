package graphing;

import org.junit.jupiter.api.*;

class WeightedGraphTests {
	
	static Parser parse = new TestParser();
	static WeightedGraph graph = new WeightedGraph(10, parse);
	

	@BeforeAll
	static void setUpBeforeClass()  {

		graph.addVertex("apple");
		graph.addVertex("pear");
		graph.addVertex("banana");
		graph.addVertex("avocado");
		graph.addVertex("mango");
		Relationship rel1 = new Relationship("apple", "pear", 1);
		Relationship rel2 = new Relationship("mango", "avocado", 2);
		Relationship rel3 = new Relationship("pear", "banana", 3);
		Relationship rel4 = new Relationship("banana", "apple", 4);
		Relationship rel5 = new Relationship("avocado", "mango", 5);
		graph.addEdge(rel1);
		graph.addEdge(rel2);
		graph.addEdge(rel3);
		graph.addEdge(rel4);
		graph.addEdge(rel5);
	}

	
	@Test
	void test1_() {
		Assertions.assertEquals(graph.findWeight("pear", "avocado"), 0);
	}

	@Test
	void test2_() {
		Assertions.assertEquals(graph.findWeight("pear", "banana"), 3);
	}

	@Test
	void test3_() {
		Assertions.assertTrue(graph.breadthFirstSearch("apple", "pear"));
	}
	
	@Test
	void test4_() {
		Assertions.assertFalse(graph.breadthFirstSearch("pear", "avocado"));
	}

	@Test
	void test5_() {
		graph.markVertex("avocado");
		Assertions.assertTrue(graph.isMarked("avocado"));
	}

	@Test
	void test6_() {
		graph.markVertex("avocado");
		graph.clearMarks();
		Assertions.assertFalse(graph.isMarked("avocado"));
	}

}
