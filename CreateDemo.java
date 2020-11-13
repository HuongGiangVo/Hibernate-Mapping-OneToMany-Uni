package hibernate.demo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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
			System.out.println("Create objects: ..");
			Instructor tempInstructor = new Instructor("Tom", "Luna", "tom@gmail.com");
						
			InstructorDetail  tempInstructorDetail = new InstructorDetail("http://acbcdfcom/youtube", "music");
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			//start transaction
			session.beginTransaction();
			//save the student object
			System.out.println("Saving the objects: "+ tempInstructor);
			//save instructor also save instructor detail because cascade ALL
			session.save(tempInstructor);
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
			
		}
		finally{
			factory.close();
			
		}

	}

}
