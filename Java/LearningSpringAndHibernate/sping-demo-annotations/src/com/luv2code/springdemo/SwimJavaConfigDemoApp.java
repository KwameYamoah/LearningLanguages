package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		// create spring configuration
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		// get bean from spring container
		SwimCoach swimCoach = context.getBean("swimCoach",SwimCoach.class);
		
		// call method on the bean
		System.out.println(swimCoach.getDailyWorkout());
		System.out.println(swimCoach.getDailyFortune());
		System.out.println(swimCoach.getTeam());
		System.out.println(swimCoach.getEmail());
		// close the context
		context.close();
	}

}
