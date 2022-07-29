package com.luv2code.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		
		try {
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file and map/convert to Java POJO : data/sample-lite.json
			Student theStudent = mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			// print first name and last name
			System.out.println("First name = " + theStudent.getFirstName());
			System.out.println("Last name = " + theStudent.getLastName());
			// print out address : street and city
			System.out.println("Street = " + theStudent.getAddress().getStreet());
			System.out.println("City = " + theStudent.getAddress().getCity());
			
			// print out languages
			for (String tempLang : theStudent.getLanguages()) {
				System.out.println("languages= " + tempLang);
			}
			
			System.out.println("active = " + theStudent.isActive());
		}catch (Exception exc){
			exc.printStackTrace();
		}

	}

}