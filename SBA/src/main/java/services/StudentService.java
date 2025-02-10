package services;

import com.Jordan.dao.StudentI;
import models.Course;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StudentService implements StudentI {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Student> getAllStudents() {
        // connect to the database and asks the database for all records in the student table.
    try (Session session = sessionFactory.openSession()) {
        return session.createQuery("from Student", Student.class).list();
        }
    }

    @Override
    public void createStudent(Student student) {
        //    check if the email exist if not throws exception
        if(student.getEmail() == null || student.getEmail().isEmpty()){
            throw new IllegalArgumentException("Student's mail cannot be empty");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction tx =  session.beginTransaction();
            session.persist(student);
            tx.commit();
        }
    }

    @Override
    public Student getStudentByEmail(String email) {
        // Connect the database and retrieves a student based on their emails.
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, email);
        }
    }

    @Override
    public boolean validateStudent(String email, String password) {
        // Call getStudentByEmail(email) to fetch the student email and check the existance  of the student then
        // compare the password stored with the entered password
        Student student = getStudentByEmail(email);
        return student!= null && student.getPassword().equals(password);
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {
        // Connect to the database and find the student and course then retrieve them.
       try (Session session = sessionFactory.openSession()) {
           Transaction tx = session.beginTransaction();
           Student student = getStudentByEmail(email);
           Course course = session.get(Course.class, courseId);

           // check if the student and course exist, if yes, we add the course to the student's list
           if (student != null && course != null) {
               student.getCourses().add(course);
               session.merge(student);
           }
           tx.commit();
       }
    }
    // fetch the student and check if the student exist, return their list of course as arrayList
    //if not return empty list.
    @Override
    public List<Course> getStudentCourses(String email) {
        Student student = getStudentByEmail(email);
        // use collection to prevent duplications.
        return student != null ? new ArrayList<>(student.getCourses()) : Collections.emptyList();
    }
}

