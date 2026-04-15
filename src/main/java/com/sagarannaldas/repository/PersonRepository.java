package com.sagarannaldas.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.sagarannaldas.entity.Person;

@Repository
public class PersonRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	public String addPerson(Person person) {
		dynamoDBMapper.save(person);
		return person.getPersonId();
	}

	public Person findPersonById(String personId) {
		return dynamoDBMapper.load(Person.class, personId);
	}

	public String deletePerson(String personId) {
		Person person = dynamoDBMapper.load(Person.class, personId);
		dynamoDBMapper.delete(person);
		return person.getPersonId() + " get deleted !";
	}

	public Person updatePerson(String personId, Person newPerson) {
		Person person = dynamoDBMapper.load(Person.class, personId);
		person.setName(newPerson.getName());
		person.setAge(newPerson.getAge());
		person.setEmail(newPerson.getEmail());
		person.setAddress(newPerson.getAddress());
		dynamoDBMapper.save(person);

//		dynamoDBMapper.save(person, buildExpression(person));
		return dynamoDBMapper.load(Person.class, personId);
	}

	private DynamoDBSaveExpression buildExpression(Person person) {

		DynamoDBSaveExpression dynamoDBSaveExpresssion = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expectedValue = new HashMap<>();
		expectedValue.put("personId", new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
		dynamoDBSaveExpresssion.setExpected(expectedValue);
		return dynamoDBSaveExpresssion;
	}

}
