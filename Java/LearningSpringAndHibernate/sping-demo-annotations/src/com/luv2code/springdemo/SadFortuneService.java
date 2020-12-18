package com.luv2code.springdemo;

public class SadFortuneService implements FortuneService {

	@Override
	public String getDailyFortune() {
		return "Unlucky day, we will get em next time";
	}

}
