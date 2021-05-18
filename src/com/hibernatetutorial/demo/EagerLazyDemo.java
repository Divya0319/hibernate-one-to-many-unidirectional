package com.hibernatetutorial.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernatetutorial.demo.entity.Course;
import com.hibernatetutorial.demo.entity.Instructor;
import com.hibernatetutorial.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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
			
			// get the instructor from db
			
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("luv2code:  Instructor: " + tempInstructor);
			
			
			
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			// since the courses are lazy loaded ... this should fail
			
			// get course for instructor
			System.out.println("luv2code:  Courses: " + tempInstructor.getCourses());
			
			System.out.println("------------       -----------------");
			System.out.println("Done!!");
			System.out.println("------------       -----------------");
			
		}
		finally {
			// add clean up code
			session.close();
			factory.close();
		}
	}

}
