package binarySearchTree;

public interface BSTinterface<T> {
	
	public static final int INORDER = 1;
	public static final int PREORDER = 2;
	public static final int POSTORDER = 3;
	
	public boolean isEmpty();
	
	public boolean isFull();
	
	public int numberOfNodes();
	
	public boolean isThere (Comparable<T> item);
	
	public Comparable<T> retrieve (Comparable<T> item);
	
	public void insert (Comparable<T> item);
	
	public void delete (Comparable<T> item);
	
	public int reset (int orderType);
	
	public Comparable<T> getNextItem(int orderType);

}
