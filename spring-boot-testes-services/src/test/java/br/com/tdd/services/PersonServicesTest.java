package br.com.tdd.services;

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

import br.com.tdd.model.Person;
import br.com.tdd.repositories.PersonRepository;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("validando PersonService")
public class PersonServicesTest {

	@Mock
	private PersonRepository repository;
	
	@InjectMocks
	private PersonService service;
	
	private Person person;
	
	@BeforeEach
	public void setup() {
		person = new Person("george", "silva", "george@gmail.com", "belem", "m");
	}
	
	@DisplayName("valida o cadastro de person")
	@Test
	public void testaCadastroDePessoa() {
		// Given / Arrange
		given(repository.findByEmail(anyString())).willReturn(Optional.empty());
		given(repository.save(person)).willReturn(person);
		
		// When / Act
		Person savedPerson = service.create(person);
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals("george", savedPerson.getFirstName());
	}

}


