package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	
		@RequestMapping("/showForm")
		public String showForm(Model theModel) {
			
			// create a student object
			Student theStudent = new Student();
			
			// add student object to the model
			theModel.addAttribute("student1", theStudent);
			// name : student, value : theStudent
			
			return "student-form";
		}
		
		@RequestMapping("/processForm")
		public String processForm(@ModelAttribute("student1") Student theStudent) {
			
			// log the input data
			System.out.println("theStudent : " + theStudent.getFirstName()
			+ " "+ theStudent.getLastName());
			System.out.println("Country Name : " + theStudent.getCountry());
			String[] a =theStudent.getOperatingSystems();
			for(String temp : a ) {
				System.out.println(temp);
			}	
			return "student-confirmation";
		}
		
}
