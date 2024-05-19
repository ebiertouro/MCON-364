package pomPomTree;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    @FXML
    Pane background; // Reference to the background pane in the FXML file
    @FXML
    private Label rootLabel; // Reference to the root label in the FXML file

    // References to the pomPom circles in the FXML file
    @FXML
    private MyCircle mahogany_pom, cardinal_pom, cerise_pom, redRibbon_pom, white_pom,
            amour_pom, mineralGreen_pom, lime_pom, java_pom, cloud_pom, turquoise_pom,
            persianBlue_pom, thunderbird_pom, tango_pom;

    private ArrayList<MyCircle> pompoms = new ArrayList<>(); // ArrayList to store all pomPom circles

    private BinarySearchTree<MyCircle> tree = new BinarySearchTree<>(); // Binary search tree to organize circles

    // Method called when the FXML file is initialized
    @FXML
    public void initialize() {
        // Add all pomPom circles to the ArrayList
        Collections.addAll(pompoms, mahogany_pom, cardinal_pom, cerise_pom, redRibbon_pom,
                white_pom, amour_pom, mineralGreen_pom, lime_pom, java_pom, cloud_pom, turquoise_pom,
                persianBlue_pom, thunderbird_pom, tango_pom);

        // Initialize the position of each circle and set mouse click event handler
        for (MyCircle circle : pompoms) {
            circle.initializePlace();
            circle.setOnMouseClicked(this::onCircleClicked);
        }
    }

    // Method called when a circle is clicked
    @FXML
    public void onCircleClicked(MouseEvent event) {
        MyCircle circle = (MyCircle) event.getSource();
        // Double click to toggle circle insertion/deletion from the tree
        if (event.getClickCount() == 2) {
            if (tree.isThere(circle)) {
                tree.delete(circle);
                circle.setCenterX(circle.getOriginalX());
                circle.setCenterY(circle.getOriginalY());
            } else {
                tree.insert(circle);
            }
            updateGUI(); // Update the GUI after insertion/deletion
        }
    }

    // Update the GUI to reflect changes in the tree
    private void updateGUI() {
        clearLines(); // Remove existing lines
        if (tree.getRoot() != null) {
            // Update positions of circles based on the tree structure
            updateCirclePosition(tree.getRoot(), 600, 50, 300);
            drawLines(tree.getRoot()); // Draw lines between parent-child circles
        }
    }

    // Update the position of circles recursively based on tree structure
    private void updateCirclePosition(BSTnode<MyCircle> node, double x, double y, double horizontalGap) {
        if (node != null) {
            MyCircle circle = node.info;
            circle.setCenterX(x);
            circle.setCenterY(y);

            double childY = y + 100;
            double halfGap = horizontalGap / 2;

            // Update position of left child recursively
            if (node.left != null) {
                updateCirclePosition(node.left, x - halfGap, childY, halfGap);
            }
            // Update position of right child recursively
            if (node.right != null) {
                updateCirclePosition(node.right, x + halfGap, childY, halfGap);
            }
        }
    }

    // Remove all lines from the background pane
    private void clearLines() {
        background.getChildren().removeIf(node -> node instanceof Line);
    }

    // Draw lines between parent and child circles recursively
    private void drawLines(BSTnode<MyCircle> node) {
        if (node != null) {
            // Draw line between parent and left child if left child exists
            if (node.left != null) {
                drawLine(node.info, node.left.info);
                drawLines(node.left); // Draw lines for left subtree
            }
            // Draw line between parent and right child if right child exists
            if (node.right != null) {
                drawLine(node.info, node.right.info);
                drawLines(node.right); // Draw lines for right subtree
            }
        }
    }

    // Draw a line between parent and child circles
    private void drawLine(MyCircle parent, MyCircle child) {
        // Create a line from parent circle center to child circle center
        Line line = new Line(parent.getCenterX(), parent.getCenterY(), child.getCenterX(), child.getCenterY());
        line.getStyleClass().add("line"); // Add CSS class for styling
        background.getChildren().add(0, line); // Add line to the background pane
    }
}
