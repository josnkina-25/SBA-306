package services;
import com.Jordan.dao.CourseI;
import models.Course;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * CourseService is a concrete class. This class implements the
 * CourseI interface, overrides all abstract service methods and
 * provides implementation for each method.
 */
public class CourseService implements CourseI {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void createCourse(Course course) {
        // check the condition if the course instructor exist, if not throw exception
        if(course.getInstructor() == null || course.getInstructor().isEmpty()){
            throw new IllegalArgumentException("Course must have an instructor");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(course);
            tx.commit();
        }
    }

    @Override
    public Course getCourseById(int courseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, courseId);
        }
    }

    @Override
    public List<Course> getAllCourses() {
       try (Session session = sessionFactory.openSession()) {
           return session.createQuery("from Course", Course.class).list();
       }
    }
}
