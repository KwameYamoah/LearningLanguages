package com.luv2code.springdemo;

public interface FortuneService {
	String[] fortunes = new String[] {
			"Today you will achieve alot","Today is your lucky day",
			"Today is the day you will succeed"};
	public String getFortune();
}
