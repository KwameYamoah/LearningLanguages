package com.test.springdemo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Person {
	
	@NotNull(message="is required")
	@Size(min=1,message="is required")
	private String firstName;
	
	public Person() {
		
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
}
