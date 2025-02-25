package br.com.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.tdd.service.IPersonService;
import br.com.tdd.service.PersonService;

public class PersonServiceTest {

	@DisplayName("quando criar uma pessoa com sucesso deve retornar um objeto de pessoa")
	@Test
	public void testeQuandoCriarUmaPessoaDeveRetornarUmObjetoDePessoa() {
		// Given / Arrange
		IPersonService service = new PersonService();
		// When / Act
		
		// Then / Assert
	}
	
}
