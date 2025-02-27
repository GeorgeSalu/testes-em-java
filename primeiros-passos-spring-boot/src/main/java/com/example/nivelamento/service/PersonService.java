package com.example.nivelamento.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nivelamento.exceptions.ResourceNotFoundException;
import com.example.nivelamento.model.Person;
import com.example.nivelamento.repository.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;
	
	public List<Person> findAll() {
		
		return repository.findAll();
	}

	public Person findById(Long id) {
		
		logger.info("find one person");
		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("nao existe pessoa com esse id"));
	}

	public Person create(Person person) {
		
		logger.info("create person");
		return repository.save(person);
	}
	
	public Person update(Person person) {
		
		logger.info("update person");
		
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("nao existe pessoa com esse id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("nao existe pessoa com esse id"));
		
		repository.delete(entity);
	}
	
}
