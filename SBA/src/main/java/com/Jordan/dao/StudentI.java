package com.Jordan.dao;

import models.Course;
import models.Student;

import java.util.List;

/**
 * The StudentI interface declares abstract methods and
 * is implemented by other classes to provide services for a student.
 */
public interface StudentI {
    // return all students from database and handle commit, rollback and exceptions.
    List<Student> getAllStudents();
    // persist student to the database and handle commit, rollback and exceptions
    void createStudent(Student student);
    // return student if exist and handle commit, rollback and exceptions
    Student getStudentByEmail(String email);

     // match email and password to database to gain access to courses.
    boolean validateStudent(String email, String password);

    //register the course to a student
    void registerStudentToCourse(String email, int courseId);
    // get all student's course list
    List<Course> getStudentCourses(String email);
}
