package pomPomTree;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    @FXML
    Pane background;
    @FXML
    private Label rootLabel;

    @FXML
    private MyCircle mahogany_pom, cardinal_pom, cerise_pom, redRibbon_pom, white_pom,
            amour_pom, mineralGreen_pom, lime_pom, java_pom, cloud_pom, turquoise_pom,
            persianBlue_pom, thunderbird_pom, tango_pom;

    private ArrayList<MyCircle> pompoms = new ArrayList<>();
    private BinarySearchTree<MyCircle> tree = new BinarySearchTree<>();

    @FXML
    public void initialize() {
        Collections.addAll(pompoms, mahogany_pom, cardinal_pom, cerise_pom, redRibbon_pom,
                white_pom, amour_pom, mineralGreen_pom, lime_pom, java_pom, cloud_pom, turquoise_pom,
                persianBlue_pom, thunderbird_pom, tango_pom);

        for (MyCircle circle : pompoms) {
            circle.initializePlace();
            circle.setOnMouseClicked(this::onCircleClicked);
        }
    }

    @FXML
    public void onCircleClicked(MouseEvent event) {
        MyCircle circle = (MyCircle) event.getSource();
        if (event.getClickCount() == 2) {
            if (tree.isThere(circle)) {
                tree.delete(circle);
                circle.setCenterX(circle.getOriginalX());
                circle.setCenterY(circle.getOriginalY());
            } else {
                tree.insert(circle);
            }
            updateGUI();
        }
    }

    private void updateGUI() {
        clearLines();
        if (tree.getRoot() != null) {
            updateCirclePosition(tree.getRoot(), 600, 50, 300);
        }
    }

    private void updateCirclePosition(BSTnode<MyCircle> node, double x, double y, double horizontalGap) {
        if (node != null) {
            MyCircle circle = node.info;
            double durationMillis = 400;

            KeyValue keyValueX = new KeyValue(circle.centerXProperty(), x);
            KeyValue keyValueY = new KeyValue(circle.centerYProperty(), y);

            KeyFrame keyFrame = new KeyFrame(Duration.millis(durationMillis), keyValueX, keyValueY);

            Timeline timeline = new Timeline(keyFrame);
            timeline.setOnFinished(event -> {
                double childY = y + 100;
                double halfGap = horizontalGap / 2;

                if (node.left != null) {
                    updateCirclePosition(node.left, x - halfGap, childY, halfGap);
                }
                if (node.right != null) {
                    updateCirclePosition(node.right, x + halfGap, childY, halfGap);
                }

                // Draw lines after all nodes are in their final positions
                if (node == tree.getRoot()) {
                    drawAllLines(tree.getRoot());
                }
            });

            timeline.play();
        }
    }

    private void clearLines() {
        background.getChildren().removeIf(node -> node instanceof Line);
    }

    private void drawAllLines(BSTnode<MyCircle> node) {
        if (node != null) {
            if (node.left != null) {
                drawLine(node.info, node.left.info);
                drawAllLines(node.left);
            }
            if (node.right != null) {
                drawLine(node.info, node.right.info);
                drawAllLines(node.right);
            }
        }
    }

    private void drawLine(MyCircle parent, MyCircle child) {
        Line line = new Line();
        line.startXProperty().bind(parent.centerXProperty());
        line.startYProperty().bind(parent.centerYProperty());
        line.endXProperty().bind(child.centerXProperty());
        line.endYProperty().bind(child.centerYProperty());
        line.getStyleClass().add("line");
        background.getChildren().add(0, line);
    }
}
