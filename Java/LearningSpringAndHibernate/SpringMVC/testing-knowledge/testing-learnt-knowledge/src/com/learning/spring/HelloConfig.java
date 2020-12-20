package com.learning.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.learning.spring")
public class HelloConfig {
	
	@Bean
	public Person person() {
		return new Person("Bob", 15);
	}
	
	
	@Bean
	public Hello hello() {
		return new Hello(person());
	}
	

}
