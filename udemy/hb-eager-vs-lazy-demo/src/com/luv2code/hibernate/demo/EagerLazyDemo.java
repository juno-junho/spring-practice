package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Luv2code : Instructor : " + tempInstructor);
		
			
			// commit transaction
			session.getTransaction().commit();
			
			// Close the session.
			session.close();
			
			System.out.println("\nluv2code : the session is now closed!\n");
			
			// since courses are lazy loaded ... this should fail
				
			// get course for the instructor
			System.out.println("Luv2code : Courses : " + tempInstructor.getCourses());
			
			System.out.println("Luv2code : Done!");
			
		} finally {
			//add clean up code
			session.close();

			factory.close();
		}
	}

}
