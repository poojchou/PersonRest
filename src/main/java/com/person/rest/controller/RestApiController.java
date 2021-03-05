package com.person.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.person.rest.model.Person;
import com.person.rest.service.PersonService;
import com.person.rest.util.CustomErrorType;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    PersonService personService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Persons--------------------------------------------

    @RequestMapping(value = "/person/", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> listAllPersons() {
        List<Person> persons = personService.findAllPersons();
        if (persons.isEmpty()) {
            return new ResponseEntity<>(persons, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    // -------------------Retrieve Single Person------------------------------------------

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPerson(@PathVariable("id") long id) {
        logger.info("Fetching Person with id {}", id);
        Person person = personService.findById(id);
        if (person == null) {
            logger.error("Person with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Person with id " + id  + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    // -------------------Create a person-------------------------------------------

    @RequestMapping(value = "/person/", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        logger.info("Creating Person : {}", person);

        if (personService.isPersonExist(person)) {
            logger.error("Unable to create. A Person with name {} already exist", person.getFirstName()+" "+person.getLastName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Product with name " +
            		person.getFirstName() + " "+person.getLastName()+" already exist."), HttpStatus.CONFLICT);
        }
        personService.savePerson(person);

        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    // ------------------- Update a Person ------------------------------------------------

    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        logger.info("Updating Person with id {}", id);

        Person currentPerson = personService.findById(id);

        if (currentPerson == null) {
            logger.error("Unable to update. Person with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. Person with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentPerson.setFirstName(person.getFirstName());
        currentPerson.setLastName(person.getLastName());
        currentPerson.setAge(person.getAge());
        currentPerson.setFavColor(person.getFavColor());

        personService.updatePerson(currentPerson);
        return new ResponseEntity<>(currentPerson, HttpStatus.OK);
    }

    // ------------------- Delete a Person-----------------------------------------

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Person with id {}", id);

        Person product = personService.findById(id);
        if (product == null) {
            logger.error("Unable to delete. Person with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Person with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        personService.deletePersonById(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Persons-----------------------------

    @RequestMapping(value = "/person/", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deleteAllPersons() {
        logger.info("Deleting All Persons");

        personService.deleteAllPersons();
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

}