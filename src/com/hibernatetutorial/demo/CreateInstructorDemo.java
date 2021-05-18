package com.hibernatetutorial.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernatetutorial.demo.entity.Course;
import com.hibernatetutorial.demo.entity.Instructor;
import com.hibernatetutorial.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
						
			// create the objects
			Instructor tempInstructor = 
					new Instructor("Susan", "Public", "susan.public@luv2code.com");
						
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.youtube.com",
							"Video Games");
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			
			// start the transaction
			session.beginTransaction();
			
			System.out.println("------------       -----------------");
			System.out.println("Saving the instructor: " + tempInstructor);
			System.out.println("------------       -----------------");
			session.save(tempInstructor);
			
			
			
			
			// commit transaction
			session.getTransaction().commit();
			
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
