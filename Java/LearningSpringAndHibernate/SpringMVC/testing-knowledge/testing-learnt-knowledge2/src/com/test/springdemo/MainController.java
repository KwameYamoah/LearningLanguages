package com.test.springdemo;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController{
	
	@RequestMapping("/")
	public String homePage(Model model) {
		model.addAttribute("person", new Person());
		return "home-page";
	}
	
	
	
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("person") Person person, BindingResult theBindingResult) {	
		
		if(theBindingResult.hasErrors()) {
			System.out.println(theBindingResult);
			return "home-page";
		}
		else {
			return "student-confirmation";
		}
		
		
	}
	
	
}
