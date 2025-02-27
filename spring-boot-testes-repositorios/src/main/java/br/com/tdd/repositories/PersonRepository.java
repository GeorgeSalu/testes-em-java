package br.com.tdd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tdd.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}