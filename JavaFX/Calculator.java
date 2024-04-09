package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calculator extends Application {
	
	//this class opens the fxml and controller files

	@Override
	public void start(Stage primaryStage) throws Exception{
	    // Load the FXML file
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Calculator.fxml"));
	    loader.setController(new CalculatorController()); // Set the controller
	    Parent root = loader.load();
	    primaryStage.setTitle("Calculator"); // Set the title of the window
	    Scene scene = new Scene(root, 462, 431); // Create a scene with the loaded FXML content
	    
	    // Set the scene to the stage
	    primaryStage.setScene(scene);
	    primaryStage.show(); // Show the stage
	}


    public static void main(String[] args) {
        launch(args);
    }
}
