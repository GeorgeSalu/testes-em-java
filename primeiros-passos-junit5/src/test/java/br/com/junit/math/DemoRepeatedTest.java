package br.com.junit.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class DemoRepeatedTest {
	
	SimpleMath math;

	@BeforeEach
	void beforeEachMethod() {
		math = new SimpleMath();
	}

	@RepeatedTest(3)
	@DisplayName("teste divisao por zero")
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
