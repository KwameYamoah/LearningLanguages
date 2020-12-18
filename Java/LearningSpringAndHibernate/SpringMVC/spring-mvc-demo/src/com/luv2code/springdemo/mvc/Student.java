package com.luv2code.springdemo.mvc;

import java.util.LinkedHashMap;

public class Student {
	private String firstName;
	private String lastName;
	private String country;
	private String favouriteLanguage;
	private String[] operationSystems;


	private LinkedHashMap<String,String> countryOptions;
	
	public Student() {
		countryOptions = new LinkedHashMap<>();
		countryOptions.put("BR","Brazil");
		countryOptions.put("FR","France");
		countryOptions.put("DE","Germany");
		countryOptions.put("IN","India");
		countryOptions.put("GH","Ghana");
	
	}
	


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

	public LinkedHashMap<String, String> getCountryOptions() {
		return countryOptions;
	}
	
	public String getFavouriteLanguage() {
		return favouriteLanguage;
	}

	public void setFavouriteLanguage(String favouriteLanguage) {
		this.favouriteLanguage = favouriteLanguage;
	}


	public String[] getOperatingSystems() {
		return operationSystems;
	}

	public void setOperatingSystems(String[] operatingSystems) {
		this.operationSystems = operatingSystems;
	}

}
