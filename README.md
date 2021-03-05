# PersonRest
Person rest API project for CRUD

Maven install to create the fat jar
	mvn clean install
Docker build
	docker build --tag=person-rest .
Docker run
	docker run -p 80:8080 person-rest
Test the app

1. To create new person:
   POST http://localhost/PersonRest/api/person/
   Sample payload:{"firstName":"Pooja","lastName":"ch","age":"34","favColor":"Pink"}

2. To list all the persons:
   GET  http://localhost/PersonRest/api/person/

3. To get a particular person, need to pass the id returned while creating the person:
   GET  http://localhost/PersonRest/api/person/{id}

4. To update the person need to pass the id returned while creating the person.
   PUT  http://localhost/PersonRest/api/person/{id}
   Sample payload:{"firstName":"Pooja","lastName":"ch","age":"34","favColor":"Green"}

5. To delete a person
   DELETE http://localhost/PersonRest/api/person/{id}

6. Delete all the persons:
   DELETE http://localhost/PersonRest/api/person/

Assumptions:
------------
Used HashMap internally to store person information(in-memory) as a POC. We can change it to any database if needed.
   
	

