package pomPomTree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PomPomTree.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("PomPom Screen"); // Set the title of the stage
            primaryStage.setScene(new Scene(root, 800, 600)); // Set the scene
            primaryStage.setFullScreen(true); 
            primaryStage.show(); // Show the stage
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
