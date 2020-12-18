package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemoApp {

	public static void main(String[] args) {
		// create spring configuration
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		// get bean from spring container
		//TennisCoach tennisCoach = context.getBean("tennisCoach",TennisCoach.class);
		
		// call method on the bean
		//System.out.println(tennisCoach.getDailyWorkout());
		//System.out.println(tennisCoach.getDailyFortune());
//		System.out.println(tennisCoach.getTeam());
//		System.out.println(tennisCoach.getEmail());
		// close the context
		context.close();
	}

}
