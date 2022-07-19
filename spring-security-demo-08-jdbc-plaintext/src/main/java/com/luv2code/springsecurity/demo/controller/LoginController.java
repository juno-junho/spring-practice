package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	// security 관련된것 모아 놓은 controller
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
//		return "plain-login";
		return "custom-login";
	}
	
	// add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenined() {
		return "access-denied";
	}

}
