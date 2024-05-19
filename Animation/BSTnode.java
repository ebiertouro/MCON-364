package pomPomTree;

public class BSTnode<T extends Comparable<T>> {
    protected T info;
    protected BSTnode<T> right;
    protected BSTnode<T> left;
    protected int depth; // Add depth field

    BSTnode(T info, int depth) { // Update constructor to include depth
        this.info = info;
        this.right = null;
        this.left = null;
        this.depth = depth;
    }
}
