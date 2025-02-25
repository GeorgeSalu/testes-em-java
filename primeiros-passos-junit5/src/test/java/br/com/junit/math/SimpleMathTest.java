package br.com.junit.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleMathTest {

	@Test
	public void testaSoma() {
		SimpleMath math = new SimpleMath();
		Double actual = math.sum(6.2D, 2D);
		double expected = 8.2D;
		
		assertEquals(expected, actual, "6.2 + 2 resultou em 8.2");
		assertNotEquals(9.2, actual);
		assertNotNull(actual);
	}
	
}
