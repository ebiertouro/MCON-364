package binarySearchTree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	
	BinarySearchTree<Integer> emptyBST = new BinarySearchTree<Integer>();
	BinarySearchTree<Integer> fullBST = new BinarySearchTree<Integer>();
	Integer num1 = 1, num2 = 2, num3 = 3, num4 = 4, num5 = 5;
	Integer[] numArray = { num1, num2, num3, num4, num5};

	@BeforeEach
	void setup(){
		fullBST.insert(num3);
		fullBST.insert(num1);
		fullBST.insert(num5);
		fullBST.insert(num2);
	}
	

	@Test
	void test1_BST_insert_when_empty() {
		emptyBST.insert(num1);
		Assertions.assertTrue(emptyBST.isThere(num1));
	}
	
	@Test
	void test2_BST_insert_when_full() {
		fullBST.insert(num4);
		Assertions.assertTrue(fullBST.isThere(4));
	}
	
	@Test
	void test3_BST_delete_root() {
		emptyBST.insert(num1);
		emptyBST.delete(num1);
		Assertions.assertTrue(emptyBST.isEmpty());
	}
	
	@Test
	void test4_BST_delete_leaf() {
		int numNodes = fullBST.numberOfNodes();
		fullBST.delete(num5);
		Assertions.assertEquals(fullBST.numberOfNodes(), (numNodes -1));
		
	}
	
	@Test
	void test5_BST_num_of_nodes() {
		int counter = 0;
		for(Integer num: numArray) {
			emptyBST.insert(num);
			counter++;
		}
		Assertions.assertEquals(counter, emptyBST.numberOfNodes());	
	}
	
	@Test
	void test6_BST_retrieve_from_root() {
		Assertions.assertEquals(fullBST.retrieve(num3), 3);
	}
	
	@Test
	void test7_BST_retrieve_from_leaf() {
		Assertions.assertEquals(fullBST.retrieve(num2), 2);
	}
	

	@Test
	void test8_BST_isThere_when_true() {
		Assertions.assertTrue(fullBST.isThere(num1));
	}

	@Test
	void test9_BST_isThere_when_false() {
		Assertions.assertFalse(emptyBST.isThere(num1));
	}
	
	@Test
	void test10_BST_printBreadthFirst_works() {
		String output = fullBST.getBreadthFirst();
		Assertions.assertEquals("3 1 5 2 ", output);
	}
}