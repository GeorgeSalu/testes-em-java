package br.com.tdd.service;

import java.util.concurrent.atomic.AtomicLong;

import br.com.tdd.model.Person;

public class PersonService implements IPersonService {

	@Override
	public Person createPerson(Person person) {
		var id = new AtomicLong().incrementAndGet();
		person.setId(id);
		return person;
	}

}
