package com.person.rest.model;

import java.util.concurrent.atomic.AtomicLong;

public class Person {

    private static final AtomicLong counter = new AtomicLong();

    private long id;

    

	private String first_name;

    private String last_name;

    private int age;
    
    private String favourite_colour;

    public Person() {
        this.id = counter.incrementAndGet();
    }

    public Person(String firstName,String lastName, int age, String favColor) {
        this.id = counter.incrementAndGet();
        this.first_name = firstName;
        this.last_name = lastName;
        this.age = age;
        this.favourite_colour = favColor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFavColor() {
		return favourite_colour;
	}

	public void setFavColor(String favColor) {
		this.favourite_colour = favColor;
	}


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + "age=" + age
                + ", favourite_color=" + favourite_colour + "]";
    }


}
