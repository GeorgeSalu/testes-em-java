package br.com.tdd.controller.integracao;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		specification = new RequestSpecBuilder()
								.setBasePath("/person")
								.setPort(TestConfigs.SERVER_PORT)
								.addFilter(new RequestLoggingFilter(LogDetail.ALL))
								.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
								.build();
		
		person = new Person("george", "silva", "george@gmail.com", "belem", "m");
	}
	
	
	@Test
	@Order(1)
	@DisplayName("JUnit integration given Person Object when create one Person should return a person object")
	public void integrationTest_when_createOnePerson_shouldReturnAPersonObject() throws JsonMappingException, JsonProcessingException {
		var content = given().spec(specification)
					.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(person)
	            .when()
	                .post()
	            .then()
	                .statusCode(200)
			            .extract()
			                .body()
			                    .asString();
		
		Person createdPerson = objectMapper.readValue(content, Person.class);
		
		person = createdPerson;
		
		assertNotNull(createdPerson);
		assertNotNull(createdPerson.getId());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());
		assertNotNull(createdPerson.getEmail());

		
		assertTrue(createdPerson.getId() > 0);
		assertEquals("george", createdPerson.getFirstName());
		assertEquals("silva", createdPerson.getLastName());
		assertEquals("belem", createdPerson.getAddress());
		assertEquals("m",createdPerson.getGender());
		assertEquals("george@gmail.com", createdPerson.getEmail());
	}
	


}
