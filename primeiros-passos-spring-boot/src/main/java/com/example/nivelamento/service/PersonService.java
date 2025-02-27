package com.example.nivelamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.nivelamento.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll() {
		
		logger.info("find all people");
		List<Person> persons = new ArrayList<Person>();
		
		for(int i = 0;i < 8;i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}

	public Person findById(String id) {
		
		logger.info("find one person");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("george");
		person.setLastName("silva");
		person.setAddress("belem");
		person.setGender("masculino");
		
		return person;
	}

	public Person create(Person person) {
		
		logger.info("create person");
		return person;
	}
	
	public Person update(Person person) {
		
		logger.info("update person");
		return person;
	}
	
	public void delete(String id) {
		
		logger.info("delete person");
	}

	private Person mockPerson(int i) {
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("perons name "+i);
		person.setLastName("last name "+i);
		person.setAddress("belem");
		person.setGender("masculino");
		
		return person;
	}
	
}
