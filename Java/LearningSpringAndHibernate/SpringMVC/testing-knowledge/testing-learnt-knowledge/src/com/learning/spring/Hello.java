package com.learning.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hello {
	private String name;
	
	@Autowired 
	public Hello(Person person) {
		this.name = person.getName();
	}
	public void greetings() {
		System.out.println("Hello " + name);
	}
	

	
	
}
