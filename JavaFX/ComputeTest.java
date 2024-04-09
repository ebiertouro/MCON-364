package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ComputeTest {

	Compute testCompute = new Compute();

	@Test
	void test1_for_simple_operations() {
		String testInput = "1+2";
		int testOutput = testCompute.evaluateExpression(testInput);
		Assertions.assertEquals(testOutput, 3);
	}

	@Test
	void test2_for_order_of_operations() {
		String testInput = "4+6/2-4/2*3";
		int testOutput = testCompute.evaluateExpression(testInput);
		Assertions.assertEquals(testOutput, 1);
	}

	@Test
	void test3_for_negative_output() {
		String testInput = "1-2";
		int testOutput = testCompute.evaluateExpression(testInput);
		Assertions.assertEquals(testOutput, -1);
	}

	@Test
	void test4_for_no_operands() {
		String testInput = "1";
		int testOutput = testCompute.evaluateExpression(testInput);
		Assertions.assertEquals(testOutput, 1);
	}

	@Test
	void test5_for_no_digits() {
		String testInput = "+-*/";
		int testOutput = testCompute.evaluateExpression(testInput);
		Assertions.assertEquals(testOutput, 0);
	}

	@Test
	void test6_for_no_input() {
		String testInput = "";
		int testOutput = testCompute.evaluateExpression(testInput);
		Assertions.assertEquals(testOutput, 0);
	}
}