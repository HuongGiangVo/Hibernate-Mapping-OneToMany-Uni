package hibernate.demo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

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
			//get a course
			int theId=9;
			Course tempCourse = session.get(Course.class, theId);
			//delete this course
			System.out.println("Deleting course: "+ tempCourse);		
			session.delete(tempCourse);//when the course is deleted => the course in instructor will be deleted
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
