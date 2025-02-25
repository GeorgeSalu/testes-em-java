package br.com.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.tdd.model.Person;
import br.com.tdd.service.IPersonService;
import br.com.tdd.service.PersonService;

public class PersonServiceTest {

	@DisplayName("quando criar uma pessoa com sucesso deve retornar um objeto de pessoa")
	@Test
	public void testeQuandoCriarUmaPessoaDeveRetornarUmObjetoDePessoa() {
		// Given / Arrange
		IPersonService service = new PersonService();
		
		Person person = new Person("keith", "moon", "keith@gmail.com", "sao paulo", "feminino");
		
		// When / Act
		Person actual = service.createPerson(person);
		
		// Then / Assert
		assertNotNull(actual);
	}
	
}
