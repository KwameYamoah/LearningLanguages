package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		// create spring configuration
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// get bean from spring container
		TennisCoach tennisCoach = context.getBean("tennisCoach",TennisCoach.class);
		
		// call method on the bean
		System.out.println(tennisCoach.getDailyWorkout());
		System.out.println(tennisCoach.getDailyFortune());
//		System.out.println(tennisCoach.getTeam());
//		System.out.println(tennisCoach.getEmail());
		// close the context
		context.close();
	}

}
