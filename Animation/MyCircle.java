package pomPomTree;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends Circle implements Comparable<MyCircle> {

    // A reference to the corresponding node in a binary search tree
    private final BSTnode<MyCircle> node;

    // Store the original coordinates of the circle
    private  double originalX;
    private  double originalY;

    // Default constructor
    public MyCircle() {
        super();
        this.node = null; // Initialize node as null
    }

    // Constructor with parameters
    public MyCircle(double radius, Color fill, BSTnode<MyCircle> treeNode, double originalX, double originalY) {
        super(radius, fill);
        this.node = treeNode; // Initialize node with the given treeNode
        this.originalX = originalX; // Initialize originalX with the given value
        this.originalY = originalY; // Initialize originalY with the given value
    }

    // Getter method for the node
    public BSTnode<MyCircle> getNode() {
        return node;
    }

    // Getter method for originalX
    public double getOriginalX() {
        return originalX;
    }

    // Getter method for originalY
    public double getOriginalY() {
        return originalY;
    }

    // Method to initialize the original place of the circle
    public void initializePlace() {
        // Set originalX and originalY to the current center coordinates of the circle
        this.originalX = this.getCenterX(); 
        this.originalY = this.getCenterY(); 
    }

    // Override compareTo method to compare circles based on their fill color
    @Override
    public int compareTo(MyCircle otherCircle) {
        // Get the fill color of this circle and the other circle
        Color color1 = (Color) this.getFill();
        Color color2 = (Color) otherCircle.getFill();
        // Get the red component of the colors
        double red1 = color1.getRed();
        double red2 = color2.getRed();
        // Compare the red components and return the result
        return Double.compare(red1, red2);
    }
}
