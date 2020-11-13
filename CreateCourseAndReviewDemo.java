package hibernate.demo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import hibernate.demo.entity.Review;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
												.configure("hibernate.cfg.xml")
												.addAnnotatedClass(Instructor.class)
												.addAnnotatedClass(InstructorDetail.class)
												.addAnnotatedClass(Course.class)
												.addAnnotatedClass(Review.class)
												.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		try {
			
			
			//start transaction
			session.beginTransaction();
			//create a course
			Course tempCourse = new Course("Java");
			
			//add some reviews
			tempCourse.addReview(new Review("Great...I love it"));
			tempCourse.addReview(new Review("Cool..I love it"));
			tempCourse.addReview(new Review("dumb course"));
			//save the course and leverage the cascade ALL
			System.out.println("Saving the course");
			System.out.println(tempCourse.getReviews());
			session.save(tempCourse);
			
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
