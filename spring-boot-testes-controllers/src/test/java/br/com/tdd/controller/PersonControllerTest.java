package br.com.tdd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdd.model.Person;
import br.com.tdd.service.PersonService;

import static org.mockito.BDDMockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@DisplayName("testes unitarios em PersonController")
@WebMvcTest
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private PersonService service;
	
	private Person person;
	
	@BeforeEach
	public void setup() {
		// given / arrange
		person = new Person("george", "silva", "george@gmail.com", "belem", "m");
	}
	
	@DisplayName("valida endpoint de criacao de person")
	@Test
	public void valida_OperacaoDeCreate_DeveRetornarOObjetoSalvo() throws JsonProcessingException, Exception {
		// Given / Arrange
		given(service.create(any(Person.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// When / Act
		ResultActions response = mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(person)));

		// Then / Assert
		response.andDo(print()).
			andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
			.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
			.andExpect(jsonPath("$.firstName", is(person.getFirstName())));
	}
	
	@DisplayName("valida endpoint que consulta todoas pessoas cadastradas")
	@Test
	public void valida_OperacaoDeFindAll_DeveRetornarTodosOsObjetosCadastrados() throws JsonProcessingException, Exception {
		// Given / Arrange
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		persons.add(new Person("leonardo", "macadamia", "leonardo@gmail.com", "belem", "m"));
		
		given(service.findAll()).willReturn(persons);

		// When / Act
		ResultActions response = mockMvc.perform(get("/person"));

		// Then / Assert
		response.andDo(print()).
			andExpect(status().isOk())
			.andExpect(jsonPath("$.size()", is(persons.size())));
	}
	
	@DisplayName("valida a operacao findById")
	@Test
	public void valida_OperacaoDeFindById_DeveRetornarOObjetoCadastrado() throws JsonProcessingException, Exception {
		// Given / Arrange
		long personId = 1l;
		given(service.findById(personId)).willReturn(person);

		// When / Act
		ResultActions response = mockMvc.perform(get("/person/{id}", personId));

		// Then / Assert
		response.andDo(print()).
			andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
			.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
			.andExpect(jsonPath("$.firstName", is(person.getFirstName())));
	}
	
}





