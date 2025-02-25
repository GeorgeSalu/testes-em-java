package br.com.junit.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("teste operacoes em SimpleMath")
public class SimpleMathTest {

	@Test
	@DisplayName("teste 6.2 + 2 = 8.2")
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
	@DisplayName("teste 6.2 - 2 = 4.2")
	public void testaSubtracao() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.subtraction(firstNumber, secondNumber);
		double expected = 4.2D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	@Test
	@DisplayName("teste 6.2 * 2 = 12.4")
	public void testaMultiplicacao() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.multiplication(firstNumber, secondNumber);
		double expected = 12.4D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	@Test
	@DisplayName("teste 6.2 / 2 = 3.1")
	public void testaDivisao() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.division(firstNumber, secondNumber);
		double expected = 3.1D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	@Test
	@DisplayName("teste raiz quadrada de 81 é 9")
	public void testaMedia() {
		SimpleMath math = new SimpleMath();
		double number = 81D;
		
		Double actual = math.squareRoot(number);
		double expected = 9D;
		
		assertEquals(expected, actual);
	}
	
}
