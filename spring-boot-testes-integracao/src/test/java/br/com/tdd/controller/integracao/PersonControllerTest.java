package br.com.tdd.controller.integracao;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tdd.testcontainers.AbstractTesteIntegracao;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PersonControllerTest extends AbstractTesteIntegracao {

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
