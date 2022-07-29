package com.text.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.test.hibernate.demo.entity.Employee;

public class CreateEmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			// create a employee object
			System.out.println("Creating new employee object...");
			Employee tempEmployee = new Employee("Anup", "Joshi", "Acme Inc");
			Employee tempEmployee1 = new Employee("Junho", "Hwang", "google");
			Employee tempEmployee2 = new Employee("chad", "darby", "udemy");
			// start a transaction
			session.beginTransaction();
			
			// save the employee object
			System.out.println("Saving the employee...");
			session.save(tempEmployee);
			session.save(tempEmployee1);
			session.save(tempEmployee2);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}





