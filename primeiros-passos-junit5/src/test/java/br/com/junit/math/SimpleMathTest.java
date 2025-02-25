package br.com.junit.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleMathTest {

	@Test
	public void testaSoma_Quando_SeisPontoDoisMaisDois_RetornaOitoPontoDois() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.sum(firstNumber, secondNumber);
		double expected = 8.2D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
		assertNotEquals(9.2, actual);
		assertNotNull(actual);
	}
	
	@Test
	public void testaSubtracao() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.subtraction(firstNumber, secondNumber);
		double expected = 4.2D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	@Test
	public void testaMultiplicacao() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.multiplication(firstNumber, secondNumber);
		double expected = 12.4D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	@Test
	public void testaDivisao() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.division(firstNumber, secondNumber);
		double expected = 3.1D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	@Test
	public void testaMedia() {
		SimpleMath math = new SimpleMath();
		double number = 81D;
		
		Double actual = math.squareRoot(number);
		double expected = 9D;
		
		assertEquals(expected, actual);
	}
	
}
