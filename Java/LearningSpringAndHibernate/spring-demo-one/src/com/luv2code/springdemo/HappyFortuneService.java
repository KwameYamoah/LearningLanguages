package com.luv2code.springdemo;

public class HappyFortuneService implements FortuneService{
	@Override
	public String getFortune() {
		return fortunes[ (int)(Math.random()*(fortunes.length))];
	}	
}
