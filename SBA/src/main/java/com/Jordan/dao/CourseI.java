package com.Jordan.dao;
import models.Course;

import java.util.List;

/**
 * The CourseI interface declares abstract methods and
 * is implemented by other classes to provide services for a course.
 */
public interface CourseI {
    // persist the course to the database, handle commit, rollback and exceptions.
    void createCourse(Course course);
    //return course if exist and handle commit, rollback and exceptions.
    Course getCourseById(int courseId);
    //return all courses from the database and handle commit, rollback and exceptions.
    List<Course> getAllCourses();

}
