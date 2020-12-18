package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

	// need a controller method to show initial form
	
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	
//	@RequestMapping("/processForm")
//	public String processForm(HttpServletRequest request, Model model) {
//		String name = request.getParameter("studentName");
//		model.addAttribute("message", name);
//		return "hello world";
//	}
	
	
	// need a controller method to process the form
	@RequestMapping("/processFormVersionTwo")
	public String processFormVersionTwo(HttpServletRequest request, Model model) {
		String result = "Yo! " +  request.getParameter("studentName").toUpperCase();
		model.addAttribute("message", result);
		return "helloworld";
	}
	
	
	@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(@RequestParam("studentName") String theName, Model model) {
		String result = "OI! " +  theName.toUpperCase();
		model.addAttribute("message", result);
		return "helloworld";
	}
	

}
