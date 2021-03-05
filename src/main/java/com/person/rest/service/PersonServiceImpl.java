package com.person.rest.service;

import org.springframework.stereotype.Service;

import com.person.rest.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service("PersonService")
public class PersonServiceImpl implements PersonService {


    //  Using two hashmaps in order to provide performance of O(1) while fetching Persons
    private static HashMap<Long, Person> Persons = new HashMap<>();
    private static HashMap<String, Long> idNameHashMap = new HashMap<>();


    public List<Person> findAllPersons() {
        // Pagination should be added...
        return new ArrayList<>(Persons.values());
    }

    public Person findById(long id) {
            return Persons.get(id);
    }

    public Person findByName(String name) {

        if (idNameHashMap.get(name) != null){
            return Persons.get(idNameHashMap.get(name));
        }

        return null;
    }

    public void savePerson(Person person) {
        synchronized (this) {    //  Critical section synchronized
            Persons.put(person.getId(), person);
            idNameHashMap.put(person.getFirstName()+" "+person.getLastName(), person.getId());
        }
    }

    public void updatePerson(Person person) {
        synchronized (this) {    //  Critical section synchronized
            Persons.put(person.getId(), person);
            idNameHashMap.put(person.getFirstName()+" "+person.getLastName(), person.getId());
        }
    }

    public void deletePersonById(long id) {
        synchronized (this) {    //  Critical section synchronized
            idNameHashMap.remove(Persons.get(id).getFirstName()+" "+Persons.get(id).getLastName());
            Persons.remove(id);
        }
    }

    public boolean isPersonExist(Person person) {
        return findByName(person.getFirstName()+" "+person.getLastName()) != null;
    }

    public void deleteAllPersons() {
        Persons.clear();
    }

	
}
