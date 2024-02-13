package binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T> implements BSTinterface<T>{
	
	/*
	 * A note about my code: as you instructed, I read the overview in the book, then
	 * wrote my own code. However, as I was debugging with JUNIT test suite, I found
	 * several bugs I did not know how to fix, so I tweaked my code according to how
	 * the book shows it. It could be that much of my code is identical to the book, 
	 * but I tried my best.
	 */
	
	//I implemented my own comparable queue based on the queue from last semester
	protected ComparableQueue<T> inOrderQueue;
	protected ComparableQueue<T> preOrderQueue;
	protected ComparableQueue<T> postOrderQueue;
	
	//this node is used to store data. each node is its own tree!
	protected class BSTnode{
		
		protected Comparable<T> info;
		protected BSTnode right;
		protected BSTnode left;
	}
	
	protected BSTnode root;
	
	//we instantiate an empty tree, the root points to null
	public BinarySearchTree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public boolean isFull() {
		return false;
		//false because a binary search tree is never full, can always add more nodes
	}
	
	//public methods call private methods for maximum code protection
	public int numberOfNodes() {
		return recNumberOfNodes(root);
	}
	
	private int recNumberOfNodes(BSTnode tree) {
		//we first write the base case - when node does not have children (each node is a tree)
		//then we return
		if(tree == null) {
			return 0;
		}
		else
			//the number of nodes is that node, plus its right children, plus its left children
			return recNumberOfNodes(tree.right) + recNumberOfNodes(tree.left) + 1;
	}
	
	public boolean isThere(Comparable<T> item){
		return recIsThere(item, root);
	}
	
	@SuppressWarnings("unchecked")
	private boolean recIsThere(Comparable<T> item, BSTnode tree){
		
		//if the tree is empty and we have not found the value, it does not exist
		if (tree == null) {
			return false;
		}
		//if value is larger, search the right subtree
		else if (item.compareTo((T) tree.info) > 0) {
			return recIsThere(item, tree.right);
		}
		//if value is smaller, search the left subtree
		else if (item.compareTo((T) tree.info) < 0){
			return recIsThere(item, tree.left);
		}
		//otherwise, that means value matches the input, so return true, found!
		else
			return true;
	}
	
	public Comparable<T> retrieve(Comparable<T> item){
		return recRetrieve(item, root);
	}
	
	@SuppressWarnings("unchecked")
	private Comparable<T> recRetrieve(Comparable<T> item, BSTnode tree){
		
		//precondition that item exists, so we don't need the null base case
		
		//if larger, search the right subtree
		if (item.compareTo((T) tree.info) > 0) {
			return recRetrieve(item, tree.right);
		}
		//if smaller, search the left subtree
		else if (item.compareTo((T) tree.info) < 0) {
			return recRetrieve(item, tree.left);
		}
		//otherwise, item is same, so its a match, retrieve it.
		else
			return tree.info;
	
	}
	
	public void insert(Comparable<T> item) {
		root = recInsert(item, root);
	}
	
	@SuppressWarnings("unchecked")
	private BSTnode recInsert(Comparable<T> item, BSTnode tree) {
		
		//if tree is empty, insert the item as the root node
		if (tree == null) {
			tree = new BSTnode();
			tree.info = item;
			tree.right = null;
			tree.left = null;
		}
		//otherwise, balance our tree by putting larger items to the right...
		else if (item.compareTo((T) tree.info) > 0) {
			tree.right = recInsert(item, tree.right);
		}
		//... and smaller values to the left
		else
			tree.left = recInsert(item, tree.left);
		//we return the tree because of the base case of empty tree = item becomes the root
		//in other cases, same root is reassigned
		return tree;
	}
	
	public void delete(Comparable<T> item) {
		//we reset the tree as a new tree without that node
		root = recDelete(item, root);
	}
	
	@SuppressWarnings("unchecked")
	private BSTnode recDelete(Comparable<T> item, BSTnode tree) {
		
		//if this is the node we want, pass to the deleting node method.
		//otherwise, keep searching
		if (item.compareTo((T) tree.info) == 0) {
			tree = deleteNode(item, tree);
		}
		//if larger, search the right subtree
		else if (item.compareTo((T) tree.info) > 0) {
			tree.right = recDelete(item, tree.right);
		}
		//if smaller, search the left subtree
		else {
			tree.left = recDelete(item, tree.left);
		}
		//we set the tree without that node and pass it back
		return tree;
		
	}
	
	private BSTnode deleteNode(Comparable<T> item, BSTnode tree) {
		
		Comparable<T> data;
		
		//if there is one child, point away from it, so that it is garbage collected
		if (tree.left == null) {
			return tree.right;
		}
		else if (tree.right == null) {
			return tree.left;
		}
		else {
			data = getPredecessor(tree.left);	
			//we choose the next-smallest number (largest of left subarray) and grab its data
			tree.info = data;
			//then we have to delete the node whose data we used
			tree.left = recDelete(data, tree.left);
			return tree;
		}
		
	}
	
	private Comparable<T> getPredecessor(BSTnode tree){
		//getting the largest value in the left subarray
		while (tree.right != null) {
			tree = tree.right;
		}
		return tree.info;
	}
	
	public int reset(int orderType) {
		
		//create a new queue to print 
		
		int numNodes = numberOfNodes();
		
		if (orderType == INORDER) {
			inOrderQueue = new ComparableQueue<T>();
			inOrder(root);
		}
		
		else if (orderType == PREORDER) {
			preOrderQueue = new ComparableQueue<T>();
			preOrder(root);
		}
		
		else if (orderType == POSTORDER) {
			postOrderQueue = new ComparableQueue<T>();
			postOrder(root);
		}
		/*
		else if (orderType == BREADTH_FIRST) {
			breadthFirstStack = new ComparableQueue<T>();
			breadthFirst(root);	
		}
		*/
		return numNodes;
		
	}
	
	public Comparable<T> getNextItem(int orderType){
		
		if (orderType == INORDER) {
			return (Comparable<T>)inOrderQueue.dequeue();
		}
		
		else if (orderType == PREORDER) {
			return (Comparable<T>)preOrderQueue.dequeue();
		}
		
		else if (orderType == POSTORDER) {
			return (Comparable<T>)postOrderQueue.dequeue();
		}
		else return null;
	}
	
	private void inOrder(BSTnode tree) {
		
		if (tree != null) {
			inOrder(tree.left);
			inOrderQueue.enqueue(tree.info);
			inOrder(tree.right);
		}
	}
	
	private void preOrder(BSTnode tree) {
		
		if (tree != null) {
			preOrderQueue.enqueue(tree.info);
			preOrder(tree.left);
			preOrder(tree.right);
		}
	}

	private void postOrder(BSTnode tree) {
	
		if (tree != null) {
			postOrder(tree.left);
			postOrder(tree.right);
			postOrderQueue.enqueue(tree.info);
	}
}
	
	public String getBreadthFirst()
	    {
		String output = "";
	        Queue<BSTnode> queue = new LinkedList<BSTnode>();
	        queue.add(root);
	        while (!queue.isEmpty()) {
	 
	            // poll() removes the present head.  
	            BSTnode tempNode = queue.poll();
	            output += tempNode.info + "\n";
	 
	            // Enqueue left child
	            if (tempNode.left != null) {
	                queue.add(tempNode.left);
	            }
	 
	            // Enqueue right child
	            if (tempNode.right != null) {
	                queue.add(tempNode.right);
	            }
	        }
	        return output;
	     }      
	

	@Override
	public String toString() {
	    if (root == null) {
	        return "Empty Tree";
	    }

	    return printTree(root, "", true);
	}

	private String printTree(BSTnode node, String prefix, boolean isLeft) {
	    if (node == null) {
	        return "";
	    }

	    String result = prefix;
	    result += isLeft ? "├── " : "└── ";
	    result += node.info + "\n";

	    result += printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
	    result += printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);

	    return result;
	}
	}