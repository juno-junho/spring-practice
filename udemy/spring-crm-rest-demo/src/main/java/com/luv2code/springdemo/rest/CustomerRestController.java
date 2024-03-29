package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
		
	// autowire the CustomerService (dependency injection)
	@Autowired
	private CustomerService customerService;
	
	// and mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
	
		return customerService.getCustomers();
	}
	
	// add mapping for GET /customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerService.getCustomer(customerId);
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		// happy path
		return theCustomer;
	}
	
	// add mapping for POST /customers - add new customer
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		// @RequsetBody -> (JSON data -> POJO)
		// also jst in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		System.out.println("Id before : " + theCustomer.getId());
//		theCustomer.setId(0);
		System.out.println("Id After: " + theCustomer.getId());
		System.out.println(theCustomer.getFirstName());
		System.out.println(theCustomer.getLastName());
		System.out.println(theCustomer.getEmail());
		
		// id가 0라면 DAO가 new customer를 insert 할것임.
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
		
	}
	
	// add mapping for PUT / customers - update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	// add mapping for DELETE / customers - delete customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCusomer(@PathVariable int customerId) {
		
		Customer tempCustomer = customerService.getCustomer(customerId);
		
		// throw exception if null (id 존재 여부 확인)
		if (tempCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer id - " + customerId;
	}
}
