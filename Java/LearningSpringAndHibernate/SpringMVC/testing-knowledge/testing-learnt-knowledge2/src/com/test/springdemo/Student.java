package com.test.springdemo;

import org.springframework.stereotype.Component;

@Component
public class Student {
	private String name;
	private String courseCode;
	
	public Student(Person person) {
		this.name = person.getName();
		this.courseCode = "LUV2CODE";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
}
