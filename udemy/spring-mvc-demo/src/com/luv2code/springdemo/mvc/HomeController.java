package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1. Create Controller class
@Controller

public class HomeController {
	
	//2. Define Controller method
	@RequestMapping("/")
	public String showPage() {
		return "main-menu";
	}
}
