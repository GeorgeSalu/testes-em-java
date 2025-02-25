package br.com.junit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Order(2)
@TestMethodOrder(MethodOrderer.Random.class)
class MethodOrderedRandonlyTest {

	@Test
	void testeA() {
		System.out.println(" teste A");
	}
	
	@Test
	void testeB() {
		System.out.println(" teste B");
	}
	
	@Test
	void testeC() {
		System.out.println(" teste C");
	}
	
	@Test
	void testeD() {
		System.out.println(" teste D");
	}

}
