package br.com.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.tdd.model.Person;
import br.com.tdd.service.IPersonService;
import br.com.tdd.service.PersonService;

public class PersonServiceTest {
	
	Person person;
	
	@BeforeEach
	void setup() {
		person = new Person("keith", "moon", "keith@gmail.com", "sao paulo", "feminino");
	}

	@DisplayName("quando criar uma pessoa com sucesso deve retornar um objeto de pessoa")
	@Test
	public void testeQuandoCriarUmaPessoaDeveRetornarUmObjetoDePessoa() {
		// Given / Arrange
		IPersonService service = new PersonService();
		
		
		// When / Act
		Person actual = service.createPerson(person);
		
		// Then / Assert
		assertNotNull(actual, () -> "o metodo createPerson() nao deve retornar null");
	}
	
	@DisplayName("quando criar uma pessoa com sucesso deve retornar um objeto de pessoa com propriedades validas")
	@Test
	public void testeQuandoCriarUmaPessoaDeveRetornarUmObjetoDePessoaComFirstName() {
		// Given / Arrange
		IPersonService service = new PersonService();
		
		
		// When / Act
		Person actual = service.createPerson(person);
		
		// Then / Assert
		assertNotNull(actual);
		assertEquals(person.getFirstName(), actual.getFirstName(), () -> "o person firstname é incorreto");
		assertEquals(person.getLastName(), actual.getLastName(), () -> "o person lastname é incorreto");
		assertEquals(person.getAddress(), actual.getAddress(), () -> "o person address é incorreto");
		assertEquals(person.getGender(), actual.getGender(), () -> "o person gender é incorreto");
		assertEquals(person.getEmail(), actual.getEmail(), () -> "o person email é incorreto");
	}
	
}
