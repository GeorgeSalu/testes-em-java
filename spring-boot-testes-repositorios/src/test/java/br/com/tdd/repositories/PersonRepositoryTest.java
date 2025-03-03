package br.com.tdd.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

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
	
	@DisplayName("testa operacao responsavel por salvar uma pessoa")
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
	
	@DisplayName("testa operacao responsavel por buscar todas pessoas")
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
	
	@DisplayName("testa operacao responsavel por buscar pessoa por id")
	@Test
	public void testaOperacaoResponsavelPorBuscarPessoaPorId() {
		// Given / Arrange
		Person person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findById(person0.getId()).get();
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals(person0.getId(), savedPerson.getId());;
	}
	
	@DisplayName("testa operacao responsavel por buscar pessoa por email")
	@Test
	public void testaOperacaoResponsavelPorBuscarPessoaPorEmail() {
		// Given / Arrange
		Person person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findByEmail(person0.getEmail()).get();
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals(person0.getId(), savedPerson.getId());;
	}
	
	@DisplayName("testa operacao responsavel por atualizar um resgistro de pessoa")
	@Test
	public void testaOperacaoResponsavelPorAtualizarUmRegistroDePessoa() {
		// Given / Arrange
		Person person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findById(person0.getId()).get();
		savedPerson.setFirstName("Leonardo");
		savedPerson.setEmail("leonardo@gmail.com.br");
		
		Person updatedPerson = repository.save(savedPerson);
		
		// Then / Assert
		assertNotNull(updatedPerson);
		assertEquals("Leonardo", updatedPerson.getFirstName());
		assertEquals("leonardo@gmail.com.br", updatedPerson.getEmail());
	}
	
	@DisplayName("testa operacao responsavel por deletar um resgistro de pessoa")
	@Test
	public void testaOperacaoResponsavelPorDeletarUmRegistroDePessoa() {
		// Given / Arrange
		Person person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");

		repository.save(person0);
		
		// When / Act
		repository.deleteById(person0.getId());
		
		Optional<Person> personOptional = repository.findById(person0.getId());
		
		// Then / Assert
		assertTrue(personOptional.isEmpty());
	}
	
	@DisplayName("testa operacao responsavel por buscar pessoa por firstName e lastName")
	@Test
	public void testaOperacaoResponsavelPorBuscarPessoaPorFirstNameELastName() {
		// Given / Arrange
		Person person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findByJPQL("george", "silva");
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals("george", savedPerson.getFirstName());
		assertEquals("silva", savedPerson.getLastName());
	}
	
	@DisplayName("testa operacao responsavel por buscar pessoa por firstName e lastName usando named parameters")
	@Test
	public void testaOperacaoResponsavelPorBuscarPessoaPorFirstNameELastNameUsandoNamedParameters() {
		// Given / Arrange
		Person person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findByJPQLNamedParameters("george", "silva");
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals("george", savedPerson.getFirstName());
		assertEquals("silva", savedPerson.getLastName());
	}
	
	@DisplayName("testa operacao responsavel por buscar pessoa por firstName e lastName usando sql nativo")
	@Test
	public void testaOperacaoResponsavelPorBuscarPessoaPorFirstNameELastNameUsandoSQLNativo() {
		// Given / Arrange
		Person person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findByNativeSQL("george", "silva");
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals("george", savedPerson.getFirstName());
		assertEquals("silva", savedPerson.getLastName());
	}

}
