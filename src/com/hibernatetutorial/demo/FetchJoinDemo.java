package com.hibernatetutorial.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernatetutorial.demo.entity.Course;
import com.hibernatetutorial.demo.entity.Instructor;
import com.hibernatetutorial.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {

		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();

		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start the transaction
			session.beginTransaction();
			
			
			// Option2: Hibernate query with HQL
			// get the instructor from db
			
			int theId = 1;

			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("luv2code: Instructor: " + tempInstructor);
			
			// commit the transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			System.out.println("\nluv2code:  The session is now closed!\n");
	
		
			
			// SOMEWHERE LATER IN PROGRAM
			// GET A NEW SESSION
			
			System.out.println("\n\nluv2code:  Opening a new session \n");
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			// get courses for a given parameter
			Query<Course> query = session.createQuery("select c from Course c "
						+ "where c.instructor.id=:theInstructorId",
						Course.class);
			
			
			// set parameter on Query
			query.setParameter("theInstructorId", theId);
			
			List<Course> tempCourses = query.getResultList();
			
			System.out.println("luv2code:  tempCourses: " + tempCourses);
			
			// now assign to instructor object in memory
			tempInstructor.setCourses(tempCourses);
			
			
			System.out.println("luv2code:  Courses: " + tempInstructor.getCourses());
						
			
			// commit transaction
			session.getTransaction().commit();
			
							
			System.out.println("------------       -----------------");
			System.out.println("luv2code:  Done!!");
			System.out.println("------------       -----------------");
			
		}
		finally {
			// add clean up code
			session.close();
			factory.close();
		}
	}

}
