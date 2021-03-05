package com.person.rest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.person.rest"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class PersonRest {

	public static void main(String[] args) {
		SpringApplication.run(PersonRest.class, args);
	}
}
