package com.sagarannaldas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sagarannaldas.entity.Person;
import com.sagarannaldas.repository.PersonRepository;

@RestController
public class DynamoDBController {

	@Autowired
	private PersonRepository personRepository;

	@PostMapping("/save-person")
	public String savePerson(@RequestBody Person person) {
		return personRepository.addPerson(person);
	}

	@GetMapping("/get-person/{personId}")
	public Person findPerson(@PathVariable String personId) {
		return personRepository.findPersonById(personId);
	}

	@DeleteMapping("/delete-person/{personId}")
	public String deletePerson(@PathVariable String personId) {
		return personRepository.deletePerson(personId);
	}

	@PutMapping("/update-person/{personId}")
	public Person updatePerson(@RequestBody Person person, @PathVariable String personId) {
		return personRepository.updatePerson(personId, person);
	}
}
