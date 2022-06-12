package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			
			// option 2: Hibernate query with HQL (i는 alias)
			
			// 아래 쿼리가 나중에 실행되면,instructor와 course를 한번에 load 할것이다. db로부터 load해서 메모리에 저장.
			Query<Instructor> query =
					session.createQuery("select i from Instructor i "
							 + "JOIN FETCH i.courses "
							 + "where i.id=:theInstructorId", Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
//			Instructor instructor = query.getSingleResult();
			
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
		
//			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Luv2code : Instructor : " + tempInstructor);
			
//			System.out.println("Luv2code : Courses : " + tempInstructor.getCourses());
			
			
			// commit transaction
			session.getTransaction().commit();
			
			// Close the session.
			session.close();
			
			System.out.println("\nluv2code : the session is now closed!\n");
			// option1 :Call getter method while session is open
			
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
