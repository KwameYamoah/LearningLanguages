package com.test.springdemo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController{
	
	@RequestMapping("/")
	public String homePage() {
		return "home-page";
	}
	
	@RequestMapping("/processForm")
	public String processForm(HttpServletRequest request, Model model) {
		
		String name = request.getParameter("name").toUpperCase();
		int age = Integer.parseInt(request.getParameter("age"));
		
		model.addAttribute("name",name);
		model.addAttribute("age",age);
		return "student-confirmation";
	}
	
	
}
