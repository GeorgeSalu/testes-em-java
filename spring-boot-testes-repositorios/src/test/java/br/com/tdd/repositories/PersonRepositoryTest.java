package br.com.tdd.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.tdd.model.Person;

@DisplayName("testes sobre o repositorio PersonRepository")
@DataJpaTest
class PersonRepositoryTest {

	@Autowired
	private PersonRepository repository;
	
	@DisplayName("quando um objeto person for salvo ele deve retornar o mesmo objeto salvo")
	@Test
	public void testaQuandoUmObjeto_ForSalvo_EleDeveRetornarORegistroSalvo() {
		// Given / Arrange
		Person person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");
		
		// When / Act
		Person savedPerson = repository.save(person0);
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertTrue(savedPerson.getId() > 0);
	}
	
	@DisplayName("quando invocar findAll do repositorio de pessoas deve retornar uma lista de pessoas")
	@Test
	public void testaQuandoBuscaTodosOsRegistrosDePerson() {
		// Given / Arrange
		Person person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");
		Person person1 = new Person("george", "silva", "george@gmail.com", "belem", "m");

		repository.save(person0);
		repository.save(person1);
		
		// When / Act
		List<Person> personList = repository.findAll();
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals(2, personList.size());;
	}

}
