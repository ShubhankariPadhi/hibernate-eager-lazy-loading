package com.sbits.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sbits.demo.entity.Course;
import com.sbits.demo.entity.Instructor;
import com.sbits.demo.entity.InstructorDetail;


public class DeleteCourseDemo {

	public static void main(String[] args) {
		
		
		
		//create sessionFactory
		SessionFactory factory=new Configuration().configure()
				           .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Instructor.class).addAnnotatedClass(Course.class).buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
		// get a course 
			int theId=13;
			Course tempCourse=session.get(Course.class,theId);
			
			//delete  the course
			System.out.println("deleting course"+tempCourse);
			
			session.delete(tempCourse);
		
		
			//commit the transaction
			session.getTransaction().commit();
			
		    
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
