package com.learning.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorld {

	public static void main(String[] args) {
		
		// define application context
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloConfig.class);	

		// get and use bean
		Hello greeter = context.getBean("hello", Hello.class);
		greeter.greetings();
		
		
		// close the context
		context.close();
	}

}
