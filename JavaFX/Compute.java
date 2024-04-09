package calculator;
import java.util.*;
import java.util.regex.*;

public class Compute {
	
	/*
	 * this class takes the user input as a string and computes it, then returns the result
	 * to the controller class
	 */


    public  int evaluateExpression(String expression) {
    	
    	if (expression == "")	//in case the '=' sign was pressed with no input
    		return 0;
    	
        // Tokenize the expression
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*|[+\\-*/]");
        Matcher matcher = pattern.matcher(expression);
        Deque<Integer> values = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        while (matcher.find()) { //digits
            String token = matcher.group();
            if (token.matches("\\d+\\.?\\d*")) {
                values.push(Integer.parseInt(token));
               
            } else { // Operators
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                	if (values.isEmpty())	//in case there is are not digits in the input
                 	   return 0;
                    applyOperation(values, operators.pop());
                }
                operators.push(token.charAt(0));
            }
        }
     
        
        while (!operators.isEmpty()) {
            applyOperation(values, operators.pop());
        }
        
        //return the answer to calculatorController
        
        return values.pop();
        
    }

    private  int precedence(char operator) {
    	
    	//ensures the correct order of operations
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private  void applyOperation(Deque<Integer> values, char operator) {
    	
    	//the actual math takes place here
        int operand2 = values.pop();
        int operand1 = values.pop();

        switch (operator) {
            case '+':
                values.push(operand1 + operand2);
                break;
            case '-':
                values.push(operand1 - operand2);
                break;
            case '*':
                values.push(operand1 * operand2);
                break;
            case '/':
                values.push(operand1 / operand2);
                break;
        }
    }
}

