package br.com.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.tdd.model.Person;
import br.com.tdd.service.IPersonService;
import br.com.tdd.service.PersonService;

public class PersonServiceTest {
	
	IPersonService service;
	Person person;
	
	@BeforeEach
	void setup() {
		service = new PersonService();
		person = new Person("keith", "moon", "keith@gmail.com", "sao paulo", "feminino");
	}

	@DisplayName("quando criar uma pessoa com sucesso deve retornar um objeto de pessoa")
	@Test
	public void testeQuandoCriarUmaPessoaDeveRetornarUmObjetoDePessoa() {
		// Given / Arrange
		
		
		// When / Act
		Person actual = service.createPerson(person);
		
		// Then / Assert
		assertNotNull(actual, () -> "o metodo createPerson() nao deve retornar null");
	}
	
	@DisplayName("quando criar uma pessoa com sucesso deve retornar um objeto de pessoa com propriedades validas")
	@Test
	public void testeQuandoCriarUmaPessoaDeveRetornarUmObjetoDePessoaComFirstName() {
		// Given / Arrange
		
		// When / Act
		Person actual = service.createPerson(person);
		
		// Then / Assert
		assertNotNull(actual);
		assertNotNull(person.getId(), () -> "person id é null");
		assertEquals(person.getFirstName(), actual.getFirstName(), () -> "o person firstname é incorreto");
		assertEquals(person.getLastName(), actual.getLastName(), () -> "o person lastname é incorreto");
		assertEquals(person.getAddress(), actual.getAddress(), () -> "o person address é incorreto");
		assertEquals(person.getGender(), actual.getGender(), () -> "o person gender é incorreto");
		assertEquals(person.getEmail(), actual.getEmail(), () -> "o person email é incorreto");
	}
	
	@DisplayName("quando criar um person sem email retornar um IllegalArgumentException")
	@Test
	public void testeCriaPerson_QuandoEmailNull_RetornarIllegalArgumentException() {
		// Given / Arrange
		person.setEmail(null);
		
		
		// When / Act
		var mensagemExperada = "o email de person nao pode esta null ou vazio";
		
		// Then / Assert
		IllegalArgumentException e = assertThrows(
				IllegalArgumentException.class, 
				() -> service.createPerson(person),
				() -> "Email em branco lanca IllegalArgumentException"
		);
		
		assertEquals(mensagemExperada, e.getMessage());
	}
	
}
