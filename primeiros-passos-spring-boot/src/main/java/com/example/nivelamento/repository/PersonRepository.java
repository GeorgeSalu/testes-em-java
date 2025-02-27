package com.example.nivelamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nivelamento.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
