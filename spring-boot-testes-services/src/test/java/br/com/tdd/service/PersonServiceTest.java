package br.com.tdd.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

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
	public void validaCadastroDePessoaComSucesso() {
		// Given / Arrange
		given(repository.findByEmail(anyString())).willReturn(Optional.empty());
		given(repository.save(person)).willReturn(person);
		
		// When / Act
		Person savedPerson = service.create(person);
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals("george", savedPerson.getFirstName());
	}
	
	@DisplayName("valida o cadastro de pessoa com email ja existente na base de dados")
	@Test
	public void validaCadastroDePessoaComEmailJaExistenteNaBaseDeDados() {
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

}


