package com.test.springdemo;




import org.springframework.stereotype.Component;

import com.test.springdemo.validation.IsNotNull;

@Component
public class Person {
	
	@IsNotNull
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
