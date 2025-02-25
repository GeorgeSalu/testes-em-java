package br.com.junit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Order(3)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MethodOrderedByOrderTest {

	@Test
	@Order(1)
	void testeC() {
		System.out.println(" teste C");
	}
	
	@Test
	@Order(2)
	void testeD() {
		System.out.println(" teste D");
	}
	
	@Test
	@Order(3)
	void testeA() {
		System.out.println(" teste A");
	}
	
	@Test
	@Order(4)
	void testeB() {
		System.out.println(" teste B");
	}
	
}
