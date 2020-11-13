package hibernate.demo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
												.configure("hibernate.cfg.xml")
												.addAnnotatedClass(Instructor.class)
												.addAnnotatedClass(InstructorDetail.class)
												.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		try {
			
			//start transaction
			session.beginTransaction();
			//get instructor detail object
			int theId=2666;
			InstructorDetail temp = session.get(InstructorDetail.class, theId);
			//print the instructor  detail
			System.out.println("temp instructor detail: "+temp);
			//print the instructor 
			System.out.println("the instructor: "+temp.getInstructor());
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//handle connection leak issue
			session.close();
			factory.close();
			
		}

	}

}
