package com.spring.hibernate_demo.dao;

import java.util.List;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

/**
 * Data access operations for instructors, instructor details, courses,
 * reviews, and students.
 */
public interface AppDao {

    void save(Instructor theInstructor);
    void update(Student student);
    void deleteStudentById(int theId);
    void deleteInstructorId(int theId);
    void update(Instructor in);
    void deleteInstructorById(int theId);
    void deleteCourseById(int theId);
    void save(Course theCourse);
    void deleteInstructorDetail(int theId);
    Instructor findInstructorById(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Course findCourseAndReviewsByCourseId(int theId);
    Course findCourseAndStudentByCourseId(int theId);
    Student findStudentAndCoursesByStudentId(int theId);

}
