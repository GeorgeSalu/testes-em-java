package br.com.tdd.service;

import java.util.concurrent.atomic.AtomicLong;

import br.com.tdd.model.Person;

public class PersonService implements IPersonService {

	@Override
	public Person createPerson(Person person) {
		
		if (person.getEmail() == null || person.getEmail().isBlank()) {
			throw new IllegalArgumentException("o email de person nao pode esta null ou vazio");
		}
		
		var id = new AtomicLong().incrementAndGet();
		person.setId(id);
		return person;
	}

}
