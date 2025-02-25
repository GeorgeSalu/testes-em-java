package br.com.junit.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("teste operacoes em SimpleMath")
public class SimpleMathTestS3 {

	SimpleMath math;
	
	@BeforeAll
	static void setup() {
		//System.out.println("before all");
	}
	
	@AfterAll
	static void cleanup() {
		//System.out.println("after all");
	}
	
	@BeforeEach
	void beforeEachMethod() {
		math = new SimpleMath();
	}
	
	@AfterEach
	void afterEachMethod() {
		//System.out.println("after each");
	}
	
	@Test
	@DisplayName("teste 6.2 + 2 = 8.2")
	public void testaSoma_Quando_SeisPontoDoisMaisDois_RetornaOitoPontoDois() {
		// given / Arrange
		
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		double expected = 8.2D;
		
		// when / Act
		Double actual = math.sum(firstNumber, secondNumber);
		
		// then / Assert
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
		assertNotEquals(9.2, actual);
		assertNotNull(actual);
	}
	
	@Test
	@DisplayName("teste 6.2 - 2 = 4.2")
	public void testaSubtracao() {
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.subtraction(firstNumber, secondNumber);
		double expected = 4.2D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	@Test
	@DisplayName("teste 6.2 * 2 = 12.4")
	public void testaMultiplicacao() {
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.multiplication(firstNumber, secondNumber);
		double expected = 12.4D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	@Test
	@DisplayName("teste 6.2 / 2 = 3.1")
	public void testaDivisao() {
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.division(firstNumber, secondNumber);
		double expected = 3.1D;
		
		assertEquals(expected, actual, () -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	@Test
	@DisplayName("teste raiz quadrada de 81 é 9")
	public void testaMedia() {
		double number = 81D;
		
		Double actual = math.squareRoot(number);
		double expected = 9D;
		
		assertEquals(expected, actual);
	}
	
	//@Disabled
	@DisplayName("teste divisao por zero")
	@Test
	public void testDivisaoPorZero() {
		
		// given
		double firstNumber = 6.2D;
		double secondNumber = 0D;
		
		var expectedMessage = "Impossivel dividir por zero";
		
		ArithmeticException actual = assertThrows(ArithmeticException.class, () -> {
			// when & then
			math.division(firstNumber, secondNumber);
		});
		
		assertEquals(expectedMessage, actual.getMessage());
	}
	
}
