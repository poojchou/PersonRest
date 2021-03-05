package com.person.rest.service;


import java.util.List;

import com.person.rest.model.Person;

public interface PersonService {
	
	Person findById(long id);
	
	Person findByName(String name);
	
	void savePerson(Person person);
	
	void updatePerson(Person person);
	
	void deletePersonById(long id);

	List<Person> findAllPersons();
	
	void deleteAllPersons();
	
	boolean isPersonExist(Person person);
	
}
