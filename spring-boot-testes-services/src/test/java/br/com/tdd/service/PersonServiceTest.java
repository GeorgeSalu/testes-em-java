package br.com.tdd.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.tdd.exceptions.ResourceNotFoundException;
import br.com.tdd.model.Person;
import br.com.tdd.repository.PersonRepository;

import static org.mockito.BDDMockito.*;

@DisplayName("testes unitarios em PersonService")
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository repository;
	
	@InjectMocks
	private PersonService service;
	
	private Person person;
	
	@BeforeEach
	public void setup() {
		person = new Person("george", "silva", "george@gmail.com", "belem", "m");
	}
	
	@DisplayName("valida o cadastro de pessoa com sucesso")
	@Test
	public void valida_CadastroDePessoaComSucesso_DeveRetornarOObjetoCadastrado() {
		// Given / Arrange
		given(repository.findByEmail(anyString())).willReturn(Optional.empty());
		given(repository.save(person)).willReturn(person);
		
		// When / Act
		Person savedPerson = service.create(person);
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals("george", savedPerson.getFirstName());
	}
	
	@DisplayName("valida o cadastro de pessoa com email ja usado por outro usuario")
	@Test
	public void valida_CadastroDePessoaComEmailJaExistenteNaBaseDeDados_DeveRetornarUmaException() {
		// Given / Arrange
		given(repository.findByEmail(anyString())).willReturn(Optional.of(person));
		
		// When / Act
		ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
			service.create(person);
		});
		
		// Then / Assert
		verify(repository, never()).save(any(Person.class));
		assertEquals("Person already exist with given email: "+person.getEmail(), ex.getMessage());
	}
	
	@DisplayName("valida a lista de pessoas fornecidade pelo metodo findAll() que deve retornar todas as pessoas existente na base")
	@Test
	public void valida_ListagemDePessoasFornecidoPorFindAll_DeveRetornarTodasAsPessoasCadastradas() {
		// Given / Arrange
		Person person2 = new Person("marcos", "pontes", "marcos@gmail.com", "belem", "m");
		
		given(repository.findAll()).willReturn(List.of(person, person2));
		
		// When / Act
		List<Person> personsList = service.findAll();
		
		// Then / Assert
		assertNotNull(personsList);
		assertEquals(2, personsList.size());
	}
	
	@DisplayName("valida a lista de pessoas fornecidade pelo metodo findAll() que deve retornar uma lista vazia")
	@Test
	public void valida_ListagemDePessoasFornecidoPorFindAll_DeveRetornarUmaListaVazia() {
		// Given / Arrange
		
		given(repository.findAll()).willReturn(Collections.emptyList());
		
		// When / Act
		List<Person> personsList = service.findAll();
		
		// Then / Assert
		assertTrue(personsList.isEmpty());
		assertEquals(0, personsList.size());
	}

	@DisplayName("valida a consulta de pessoa por id com sucesso deve retornar o objeto cadastrado")
	@Test
	public void valida_ConsultaDePessoaPorIdComSucesso_DeveRetornarOObjetoCadastrado() {
		// Given / Arrange
		given(repository.findById(anyLong())).willReturn(Optional.of(person));
		
		// When / Act
		Person savedPerson = service.findById(1l);
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals("george", savedPerson.getFirstName());
	}
	
	@DisplayName("valida a atualizacao de pessoa e deve retornar o objeto atualizado")
	@Test
	public void valida_AtualizaDePessoaComSucesso_DeveRetornarOObjetoAtualizado() {
		// Given / Arrange
		person.setId(1l);
		given(repository.findById(anyLong())).willReturn(Optional.of(person));
		
		person.setFirstName("Leonardo");
		person.setEmail("leonardo@gmail.com");
		
		given(repository.save(person)).willReturn(person);
		
		// When / Act
		Person updatedPerson = service.update(person);
		
		// Then / Assert
		assertNotNull(updatedPerson);
		assertEquals("Leonardo", updatedPerson.getFirstName());
		assertEquals("leonardo@gmail.com", updatedPerson.getEmail());
	}
	
}


