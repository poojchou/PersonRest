package com.person.rest;

import org.springframework.web.client.RestTemplate;

import com.person.rest.model.Person;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;


public class PersonRestTestClient {

    public static final String REST_SERVICE_URI = "http://localhost:8080/PersonRest/api";

    /* POST */
    private static void createPerson() {
        System.out.println("Testing create Person API----------");
        RestTemplate restTemplate = new RestTemplate();
        Person product = new Person("A","B", 30, "Red");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/person/", product, Person.class);
        System.out.println("Location : " + uri.toASCIIString());
    }

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllPersons() {
        System.out.println("Testing listAllPersons API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> personsMap = restTemplate.getForObject(REST_SERVICE_URI + "/person/", List.class);

        if (personsMap != null) {
            for (LinkedHashMap<String, Object> map : personsMap) {
                System.out.println("Person : id=" + map.get("id") + ", FirstName=" + map.get("firstName") +", LastName="+ map.get("lastName") + ", age=" + map.get("age") + ", FovuriteColor=" + map.get("favColor"));
            }
        } else {
            System.out.println("No Person exist----------");
        }
    }

    /* GET */
    private static void getPerson() {
        System.out.println("Testing getPerson API----------");
        RestTemplate restTemplate = new RestTemplate();
        Person person = restTemplate.getForObject(REST_SERVICE_URI + "/person/1", Person.class);
        System.out.println(person);
    }

    /* PUT */
    private static void updatePerson() {
        System.out.println("Testing update Person API----------");
        RestTemplate restTemplate = new RestTemplate();
        Person person = new Person("A","B", 30, "Green");
        restTemplate.put(REST_SERVICE_URI + "/person/1", person);
        System.out.println(person);
    }

    /* DELETE */
    private static void deletePerson() {
        System.out.println("Testing delete Person API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/person/1");
    }


    /* DELETE */
    private static void deleteAllPersons() {
        System.out.println("Testing all delete Persons API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/person/");
    }

    public static void main(String args[]) {
        listAllPersons();
        getPerson();
        createPerson();
        listAllPersons();
        updatePerson();
        listAllPersons();
        deletePerson();
        listAllPersons();
        deleteAllPersons();
        listAllPersons();
    }
}