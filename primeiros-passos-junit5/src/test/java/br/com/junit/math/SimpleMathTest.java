package br.com.junit.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleMathTest {

	@Test
	public void testaSoma() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.sum(firstNumber, secondNumber);
		double expected = 8.2D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em 8.2");
		assertNotEquals(9.2, actual);
		assertNotNull(actual);
	}
	
}
