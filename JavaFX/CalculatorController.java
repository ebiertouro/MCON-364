package calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
	
	int output;	
	String input = "";	
	Compute compute = new Compute();	//a class which processes the input and returns the output

    @FXML
    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine,
    	Zero, Enter, Clear, Addition, Subtraction, Multiplication, Division;

    @FXML
    private TextField outputText;

    // Event handling methods for button clicks
    
    //display the input as the user inputs it
    
    @FXML
    private void input1() {
    	input += "1";
    	outputText.setText(input);
    }

    @FXML
    private void input2() {
    	input += "2";
    	outputText.setText(input);
    }
    
    @FXML
    private void input3() {
    	input += "3";
    	outputText.setText(input);
    }
    
    @FXML
    private void input4() {
    	input += "4";
    	outputText.setText(input);
    }

    @FXML
    private void input5() {
        input += "5";
        outputText.setText(input);
    }
    
	@FXML
	private void input6() {
		input += "6";
		outputText.setText(input);
	}

	@FXML
	private void input7() {
		input += "7";
		outputText.setText(input);
	}

	@FXML
	private void input8() {
		input += "8";
		outputText.setText(input);
	}

	@FXML
	private void input9() {
		input += "9";
		outputText.setText(input);
	}

	@FXML
	private void input0() {
		input += "0";
	    outputText.setText(input);
   }

   @FXML
   private void Clear() {
	   //set everything to its original value
       output = 0;
       input = "";
       outputText.setText(input);
   }

   @FXML
   private void Add() {
       input += "+";
       outputText.setText(input);
   }

   @FXML
   private void Subtract() {
       input += "-";
       outputText.setText(input);
   }

   @FXML
   private void Multiply() {
       input += "*";
       outputText.setText(input);
   }

   @FXML
   private void Divide() {
       input += "/";
       outputText.setText(input);
   }
   
   @FXML
   private void Enter() {
       if (!input.isEmpty()) {
    	   //pass the input to the Compute class
           output = compute.evaluateExpression(input);
       }
       outputText.setText(Integer.toString(output));
       //reset the input
       input = "";
   }


}
