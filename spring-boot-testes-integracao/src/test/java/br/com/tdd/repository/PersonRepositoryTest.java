package br.com.tdd.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.tdd.model.Person;
import br.com.tdd.testcontainers.AbstractTesteIntegracao;

@DisplayName("testes unitarios em PersonRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest extends AbstractTesteIntegracao {

	@Autowired
	private PersonRepository repository;
	
	private Person person0;
	
	@BeforeEach
	public void setup() {
		person0 = new Person("george", "silva", "george@gmail.com", "belem", "m");
	}
	
	@DisplayName("operacao responsavel por salvar uma pessoa")
	@Test
	public void valida_QuandoUmObjetoForSalvo_DeveRetornarORegistroSalvo() {
		// Given / Arrange
		
		// When / Act
		Person savedPerson = repository.save(person0);
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertTrue(savedPerson.getId() > 0);
	}
	
	@DisplayName("operacao responsavel por buscar todas pessoas")
	@Test
	public void valida_QuandoBuscaTodosOsRegistrosDePerson() {
		// Given / Arrange
		Person person1 = new Person("george", "silva", "george@gmail.com", "belem", "m");

		repository.save(person0);
		repository.save(person1);
		
		// When / Act
		List<Person> personList = repository.findAll();
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals(2, personList.size());;
	}
	
	@DisplayName("operacao responsavel por buscar pessoa por id")
	@Test
	public void valida_OperacaoResponsavelPorBuscarPessoaPorId_DeveRetornarOObjetoComIdBuscado() {
		// Given / Arrange

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findById(person0.getId()).get();
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals(person0.getId(), savedPerson.getId());;
	}
	
	@DisplayName("operacao responsavel por buscar pessoa por email")
	@Test
	public void valida_OperacaoResponsavelPorBuscarPessoaPorEmail_DeveRetornarOObjetoComEmailBuscado() {
		// Given / Arrange

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findByEmail(person0.getEmail()).get();
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals(person0.getId(), savedPerson.getId());;
	}
	
	@DisplayName("operacao responsavel por atualizar um resgistro de pessoa")
	@Test
	public void valida_OperacaoResponsavelPorAtualizarUmRegistroDePessoa__DeveRetornarOObjetoAtualizado() {
		// Given / Arrange

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
	
	@DisplayName("operacao responsavel por deletar um resgistro de pessoa")
	@Test
	public void valida_OperacaoResponsavelPorDeletarUmRegistroDePessoa() {
		// Given / Arrange

		repository.save(person0);
		
		// When / Act
		repository.deleteById(person0.getId());
		
		Optional<Person> personOptional = repository.findById(person0.getId());
		
		// Then / Assert
		assertTrue(personOptional.isEmpty());
	}
	
	@DisplayName("operacao responsavel por buscar pessoa por firstName e lastName")
	@Test
	public void valida_OperacaoResponsavelPorBuscarPessoaPorFirstNameELastName_DeveRetornarOObjetoBuscado() {
		// Given / Arrange

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findByJPQL("george", "silva");
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals("george", savedPerson.getFirstName());
		assertEquals("silva", savedPerson.getLastName());
	}
	
	@DisplayName("operacao responsavel por buscar pessoa por firstName e lastName usando named parameters")
	@Test
	public void valida_OperacaoResponsavelPorBuscarPessoaPorFirstNameELastNameUsandoNamedParameters_DeveRetornarOObjetoBuscado() {
		// Given / Arrange

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findByJPQLNamedParameters("george", "silva");
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals("george", savedPerson.getFirstName());
		assertEquals("silva", savedPerson.getLastName());
	}
	
	@DisplayName("operacao responsavel por buscar pessoa por firstName e lastName usando sql nativo")
	@Test
	public void valida_OperacaoResponsavelPorBuscarPessoaPorFirstNameELastNameUsandoSQLNativo_DeveRetornarOObjetoBuscado() {
		// Given / Arrange

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findByNativeSQL("george", "silva");
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals("george", savedPerson.getFirstName());
		assertEquals("silva", savedPerson.getLastName());
	}
	
	@DisplayName("operacao responsavel por buscar pessoa por firstName e lastName usando sql nativo e named parameters")
	@Test
	public void valida_OperacaoResponsavelPorBuscarPessoaPorFirstNameELastNameUsandoSQLNativoENamedParameters_DeveRetornarOObjetoBuscado() {
		// Given / Arrange

		repository.save(person0);
		
		// When / Act
		Person savedPerson = repository.findByNativeSQLNamedParameters("george", "silva");
		
		// Then / Assert
		assertNotNull(person0);
		assertEquals("george", savedPerson.getFirstName());
		assertEquals("silva", savedPerson.getLastName());
	}

}
