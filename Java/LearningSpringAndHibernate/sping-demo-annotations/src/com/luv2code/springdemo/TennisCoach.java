package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

	@Autowired
	@Qualifier("sadFortuneService")
	private FortuneService fortuneService;
	
	/*
	@Autowired
	public TennisCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	*/
	
	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}
	
	/*
	 @Autowired public void setFortuneService(FortuneService fortuneService) {
	 this.fortuneService = fortuneService; }
	 */
	
	// define my initialise method
	@PostConstruct
	public void doMyStartUpStuff() {
		System.out.println(">> TennisCoach: inside doMyStartUpStuff");
	}
	
	// define my destroy method
	@PreDestroy
	public void doMyCleanUpStuff() {
		System.out.println(">> TennisCoach: inside doMyCleanUpStuff");
	}
	


	@Override
	public String getDailyFortune() {
		return fortuneService.getDailyFortune();
	}


	
}
