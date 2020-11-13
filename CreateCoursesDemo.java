package hibernate.demo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
												.configure("hibernate.cfg.xml")
												.addAnnotatedClass(Instructor.class)
												.addAnnotatedClass(InstructorDetail.class)
												.addAnnotatedClass(Course.class)
												.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		try {
			
			
			//start transaction
			session.beginTransaction();
			//get the instructor from db
			int theId = 6;
			Instructor tempIns = session.get(Instructor.class, theId);
			
			//create some courses
			Course tempCourse3 = new Course("chacha");
			Course tempCourse4 = new Course("rumba");
			
			//add courses to instructor
			tempIns.add(tempCourse3);
			tempIns.add(tempCourse4);
			//save courses
			session.save(tempCourse3);
			session.save(tempCourse4);
				
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
			
		}
		finally{
			session.close();
			factory.close();
			
		}

	}

}
