package br.com.tdd.controller.integracao;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

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
	@DisplayName("Teste de integracao JUnit dado um objeto pessoa na operacao de create com sucesso deve retornar um objeto pessoa")
	public void testeDeIntegracao_Quando_CriarPessoaComSucesso_deveRetornarObjetoPessoa() throws JsonMappingException, JsonProcessingException {
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
	
	@Test
	@Order(2)
	@DisplayName("Teste de integracao JUnit dado um objeto pessoa na operacao de update com sucesso deve retornar um objeto pessoa")
	public void testeDeIntegracao_Quando_AtualizandoPessoaComSucesso_deveRetornarObjetoPessoa() throws JsonMappingException, JsonProcessingException {
		
		person.setFirstName("leonardo");
		person.setEmail("leonardo@gmail.com");
		
		var content = given().spec(specification)
					.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(person)
	            .when()
	                .put()
	            .then()
	                .statusCode(200)
			            .extract()
			                .body()
			                    .asString();
		
		Person updatePerson = objectMapper.readValue(content, Person.class);
		
		person = updatePerson;
		
		assertNotNull(updatePerson);
		assertNotNull(updatePerson.getId());
		assertNotNull(updatePerson.getFirstName());
		assertNotNull(updatePerson.getLastName());
		assertNotNull(updatePerson.getAddress());
		assertNotNull(updatePerson.getGender());
		assertNotNull(updatePerson.getEmail());

		
		assertTrue(updatePerson.getId() > 0);
		assertEquals("leonardo", updatePerson.getFirstName());
		assertEquals("silva", updatePerson.getLastName());
		assertEquals("belem", updatePerson.getAddress());
		assertEquals("m",updatePerson.getGender());
		assertEquals("leonardo@gmail.com", updatePerson.getEmail());
	}
	
	@Test
	@Order(3)
	@DisplayName("Teste de integracao JUnit consulta de pessoa por id deve retornar um objeto pessoa")
	public void testeDeIntegracao_Quando_ConsultaPessoaPorId_deveRetornarObjetoPessoa() throws JsonMappingException, JsonProcessingException {
		
		
		var content = given().spec(specification)
				.pathParam("id", person.getId())
	            .when()
	                .get("{id}")
	            .then()
	                .statusCode(200)
			            .extract()
			                .body()
			                    .asString();
		
		Person findByIdPerson = objectMapper.readValue(content, Person.class);
		
		
		assertNotNull(findByIdPerson);
		assertNotNull(findByIdPerson.getId());
		assertNotNull(findByIdPerson.getFirstName());
		assertNotNull(findByIdPerson.getLastName());
		assertNotNull(findByIdPerson.getAddress());
		assertNotNull(findByIdPerson.getGender());
		assertNotNull(findByIdPerson.getEmail());

		
		assertTrue(findByIdPerson.getId() > 0);
		assertEquals("leonardo", findByIdPerson.getFirstName());
		assertEquals("silva", findByIdPerson.getLastName());
		assertEquals("belem", findByIdPerson.getAddress());
		assertEquals("m",findByIdPerson.getGender());
		assertEquals("leonardo@gmail.com", findByIdPerson.getEmail());
	}
	
	@Test
	@Order(4)
	@DisplayName("Teste de integracao JUnit consulta de pessoa por id deve retornar um objeto pessoa")
	public void testeDeIntegracao_Quando_ConsultaTodasAsPessoa_deveRetornarUmaListaDeTodasPessoa() throws JsonMappingException, JsonProcessingException {
		
		given().spec(specification)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.body(new Person("gabriela", "silva", "gabriela@gmail.com", "belem", "m"))
	    .when()
	        .post()
	    .then()
	        .statusCode(200)
	            .extract()
	                .body()
	                    .asString();
		
		var content = given().spec(specification)
	            .when()
	                .get()
	            .then()
	                .statusCode(200)
			            .extract()
			                .body()
			                    .asString();
		
		List<Person> listPersons = Arrays.asList( objectMapper.readValue(content, Person[].class) );
		
		Person person = listPersons.get(0);
		
		assertNotNull(person);
		assertNotNull(person.getId());
		assertNotNull(person.getFirstName());
		assertNotNull(person.getLastName());
		assertNotNull(person.getAddress());
		assertNotNull(person.getGender());
		assertNotNull(person.getEmail());

		
		assertTrue(person.getId() > 0);
		assertEquals("leonardo", person.getFirstName());
		assertEquals("silva", person.getLastName());
		assertEquals("belem", person.getAddress());
		assertEquals("m",person.getGender());
		assertEquals("leonardo@gmail.com", person.getEmail());
	}

}
