package com.sbits.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.Query;
import com.sbits.demo.entity.Course;
import com.sbits.demo.entity.Instructor;
import com.sbits.demo.entity.InstructorDetail;


public class EagerLazyDemo {

	public static void main(String[] args) {
		
		
		
		//create sessionFactory
		SessionFactory factory=new Configuration().configure()
				           .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Instructor.class).addAnnotatedClass(Course.class).buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
		int theId=1;
		org.hibernate.query.Query<Instructor> query=session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId", Instructor.class);
		
		//set parameter on query	
		query.setParameter("theInstructorId",theId);
		
		//execute query
		Instructor tempInstructor=query.getSingleResult();
		
		System.out.println("instructor:" +tempInstructor);
		
		
		
		//commit the  transaction
		session.getTransaction().commit();
		
		//close the session
		session.close();
		
		System.out.println("\n session has been closed\n");
		
		System.out.println("courses "+ tempInstructor.getCourses());
		
		    
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
