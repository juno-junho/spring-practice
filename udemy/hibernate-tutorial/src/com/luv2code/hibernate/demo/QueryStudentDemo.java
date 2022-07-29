package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
		
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			// hibernate로 database를 query해서 모든 student object의 리스트를 준다.
			
			// display the students
			displayStudents(theStudents);
			
			//query students: lastName="Doe"
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			// query students: lastName='Doe' OR firstName='Daffu'
			// s.lastName앞에 공백있는것 유의.
			theStudents = 
						session.createQuery("from Student s where"
								+ " s.lastName='Doe' OR s.firstName='Daffu'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of Doe OR first name Daffu");
			displayStudents(theStudents);
			
			// query students where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where "
					+ "s.email LIKE '%gmail.com'").getResultList();	
			
			// display the students
			System.out.println("\n\nStudents whose email ends with luv2code.com");
			displayStudents(theStudents);			
						
						
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
