package br.com.junit.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("teste operacoes em SimpleMath")
public class SimpleMathTestS4 {

	SimpleMath math;

	@BeforeEach
	void beforeEachMethod() {
		math = new SimpleMath();
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Pele", "Senna", "Keith"})
	public void testeValueSource(String firstName) {
		assertNotNull(firstName);
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
	
	@DisplayName("teste 6.2 / 2 = 3.1")
	@ParameterizedTest
	//@MethodSource("testaDivisaoEntradaParametros")
	@CsvSource({
		"6.2, 2, 3.1",
		"71, 14, 5.07",
		"18.3, 3.1, 5.90"
	})
	public void testaDivisao(double firstNumber,double secondNumber,double expected) {
		
		Double actual = math.division(firstNumber, secondNumber);
		
		assertEquals(expected, actual,2D ,() -> firstNumber +" + "+secondNumber+" nao resultou em "+expected+"");
	}
	
	public static Stream<Arguments> testaDivisaoEntradaParametros() {
		return Stream.of(
				Arguments.of(6.2D, 2D, 3.1D),
				Arguments.of(71D, 14D, 5.07D),
				Arguments.of(18.3, 3.1D, 5.90D)
		);
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
