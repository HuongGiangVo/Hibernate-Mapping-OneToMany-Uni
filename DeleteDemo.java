package hibernate.demo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

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
			//get instructor by primary key id
			int theId = 1;
			Instructor temp = session.get(Instructor.class, theId);
			System.out.println("Found the instructor: "+ temp);
			//delete the instructor
			if(temp !=null) {
				System.out.println(" delete: " + temp);
				//this also delete instructor detail because cascade type ALL
				session.delete(temp);
			}
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
			
		}
		finally{
			factory.close();
			
		}

	}

}
