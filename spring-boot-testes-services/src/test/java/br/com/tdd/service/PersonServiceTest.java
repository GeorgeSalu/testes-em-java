package br.com.tdd.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

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

@DisplayName("validando PersonService")
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
	
	@DisplayName("validacao para lista de pessoas fornecidade pelo metodo findAll() que deve retornar todas as pessoas")
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

}


