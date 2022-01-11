package com.text.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.test.hibernate.demo.entity.Employee;

public class UpdateEmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			session.beginTransaction();
			
			Employee tempEmployee = session.get(Employee.class, 4);
			System.out.println(tempEmployee);
//			session.delete(tempEmployee);
	
		
			
			session.createQuery("delete from Employee where id=2").executeUpdate();
			System.out.println("Done!");
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}
}
