package com.test.springdemo;

import org.springframework.stereotype.Component;

@Component
public class Person {
	private String name;
	private int age;
	
	public Person() {
		this.name = "Kwame";
		this.age = 12;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
