package pomPomTree;

import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T>> {
    protected BSTnode<T> root;
 

    protected Comparator<T> comparator; // Add comparator field

    // Constructor with custom comparator
    public BinarySearchTree(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
    }

    // we instantiate an empty tree, the root points to null
    public BinarySearchTree() {
        root = null;
    }
    
    // we clear by pointing root to null and then garbage collection deletes all
    public void clear() {
    	root = null;
    }

    protected BSTnode getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean isFull() {
        return false;
        // false because a binary search tree is never full, can always add more nodes
    }

    // public methods call private methods for maximum code protection
    public int numberOfNodes() {
        return recNumberOfNodes(root);
    }

    private int recNumberOfNodes(BSTnode tree) {
        // we first write the base case - when node does not have children (each node is
        // a tree)
        // then we return
        if (tree == null) {
            return 0;
        } else
            // the number of nodes is that node, plus its right children, plus its left
            // children
            return recNumberOfNodes(tree.right) + recNumberOfNodes(tree.left) + 1;
    }

    public boolean isThere(Comparable<T> item) {
        return recIsThere(item, root);
    }

    
    private boolean recIsThere(Comparable<T> item, BSTnode tree) {

        // if the tree is empty and we have not found the value, it does not exist
        if (tree == null) {
            return false;
        }
        // if value is larger, search the right subtree
        else if (item.compareTo((T) tree.info) > 0) {
            return recIsThere(item, tree.right);
        }
        // if value is smaller, search the left subtree
        else if (item.compareTo((T) tree.info) < 0) {
            return recIsThere(item, tree.left);
        }
        // otherwise, that means value matches the input, so return true, found!
        else
            return true;
    }

    public Comparable<T> retrieve(Comparable<T> item) {
        return recRetrieve(item, root);
    }

    private Comparable<T> recRetrieve(Comparable<T> item, BSTnode tree) {

        // precondition that item exists, so we don't need the null base case

        // if larger, search the right subtree
        if (item.compareTo((T) tree.info) > 0) {
            return recRetrieve(item, tree.right);
        }
        // if smaller, search the left subtree
        else if (item.compareTo((T) tree.info) < 0) {
            return recRetrieve(item, tree.left);
        }
        // otherwise, item is same, so its a match, retrieve it.
        else
            return tree.info;

    }

    public void insert(T item) {
        // Update method signature to use type parameter <T>
        root = recInsert(item, root, 0); // Pass depth parameter 0
    }

    private <T extends Comparable<T>> BSTnode<T> recInsert(T item, BSTnode<T> tree, int depth) {
        // Update method signature to use type parameter <T>
        // if tree is empty, insert the item as the root node
        if (tree == null) {
            tree = new BSTnode<>(item, depth); // Pass depth parameter
        }
        // otherwise, balance our tree by putting larger items to the right...
        else if (item.compareTo(tree.info) > 0) {
            tree.right = recInsert(item, tree.right, depth + 1);
        }
        // ... and smaller values to the left
        else {
            tree.left = recInsert(item, tree.left, depth + 1);
        }
        // we return the tree because of the base case of empty tree = item becomes the root
        // in other cases, same root is reassigned
        return tree;
    }

    public void delete(Comparable<T> item) {
        // we reset the tree as a new tree without that node
        root = recDelete(item, root);
    }

    @SuppressWarnings("unchecked")
    private BSTnode recDelete(Comparable<T> item, BSTnode tree) {

        // if this is the node we want, pass to the deleting node method.
        // otherwise, keep searching
        if (item.compareTo((T) tree.info) == 0) {
            tree = deleteNode(item, tree);
        }
        // if larger, search the right subtree
        else if (item.compareTo((T) tree.info) > 0) {
            tree.right = recDelete(item, tree.right);
        }
        // if smaller, search the left subtree
        else {
            tree.left = recDelete(item, tree.left);
        }
        // we set the tree without that node and pass it back
        return tree;

    }

    private BSTnode deleteNode(Comparable<T> item, BSTnode tree) {

        Comparable<T> data;

        // if there is one child, point away from it, so that it is garbage collected
        if (tree.left == null) {
            return tree.right;
        } else if (tree.right == null) {
            return tree.left;
        } else {
            data = getPredecessor(tree.left);
            // we choose the next-smallest number (largest of left subarray) and grab its
            // data
            tree.info = data;
            // then we have to delete the node whose data we used
            tree.left = recDelete(data, tree.left);
            return tree;
        }

    }

    private Comparable<T> getPredecessor(BSTnode tree) {
        // getting the largest value in the left subarray
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree.info;
    }


}
