package br.com.tdd.controller.integracao;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdd.config.TestConfigs;
import br.com.tdd.model.Person;
import br.com.tdd.testcontainers.AbstractTesteIntegracao;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PersonControllerTest extends AbstractTesteIntegracao {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static Person person;
	
	@BeforeAll
	public void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		specification = new RequestSpecBuilder()
								.setBasePath("")
								.setPort(TestConfigs.SERVER_PORT)
								.addFilter(new RequestLoggingFilter(LogDetail.ALL))
								.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
								.build();
		
		person = new Person("george", "silva", "george@gmail.com", "belem", "m");
	}
	


}
