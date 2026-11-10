package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * JPA implementation of {@link AppDao} using an {@link EntityManager}.
 */
@Repository 
public class AppDaoImplement implements AppDao{

    private EntityManager em;
    
    /**
    * Creates the DAO with the given entity manager.
    *
    * @param em the entity manager injected by Spring
    */
    public AppDaoImplement(EntityManager em){
        this.em = em;
    }

    /**
     * Saves a new instructor (detail is saved too, via cascade).
     */
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    /**
     * Finds an instructor by id, or returns null if not found.
     */
    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    /**
     * Deletes an instructor by id, along with its instructor
     * detail (cascade).
     */
    @Override
    @Transactional
    public void deleteInstructorId(int theId) {
        Instructor in = em.find(Instructor.class, theId);
        em.remove(in);  
    }

    /**
     * Finds an instructor detail by id, or returns null if
     * not found.
     */
    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    /**
    * {@inheritDoc}
    * Unlinks the detail from its instructor before removing it,
    * so the instructor is not deleted too.
    */
    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        deets.getInstructor().setInstructorDeets(null);
        em.remove(deets);
    }

    /**
     * Returns all courses taught by the instructor with the
     * given id.
     */
    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = em.createQuery(
            "FROM Course WHERE instructor.id=:data", Course.class);
        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    /**
     * Finds an instructor with their courses and detail loaded
     * in one query (JOIN FETCH).
     */
    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery <Instructor> query = em.createQuery(
                            "SELECT i from Instructor i " +
                            " JOIN FETCH i.courses"+
                            " JOIN FETCH i.instructorDeets" +
                            " WHERE i.id=:data",
                            Instructor.class);
            
        query.setParameter("data", theId);

        Instructor in = query.getSingleResult();
        return in;
    }

    /**
     * Updates an existing instructor (merge).
     */
    @Override
    @Transactional
    public void update(Instructor in) {
        em.merge(in);
    }

    /**
    * {@inheritDoc}
    * Sets each course's instructor to null first, so courses survive.
    */
    @Override
    @Transactional
    public void deleteInstructorById(int theId) { 
        Instructor in = em.find(Instructor.class, theId);

        List<Course> courses = in.getCourses();
        for(Course c: courses) {
            c.setInstructor(null);
        } 
        em.remove(in);
    }

    /**
     * Deletes a course by id, only if it exists.
     */
    @Override
    @Transactional 
    public void deleteCourseById(int theId) {
        Course in = em.find(Course.class, theId);
        if(in != null){
        em.remove(in);
        }
    }

    /**
     * Saves a new course (reviews are saved too, via cascade).
     */
    @Override
    @Transactional
    public void save(Course theCourse) {
        em.persist(theCourse);
    }

    /**
     * Finds a course with its reviews loaded in one query
     * (JOIN FETCH).
     */
    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
            "SELECT c FROM Course c " +
            "JOIN FETCH c.review " + 
            "WHERE c.id=:data", Course.class);
        query.setParameter("data", theId);

        Course course = query.getSingleResult();
        return course;
    }

    /**
     * Finds a course with its enrolled students loaded in one
     * query (JOIN FETCH).
     */
    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
            "SELECT c FROM Course c "+
            "JOIN FETCH c.students "+
            "WHERE c.id=:data", Course.class);
        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }

    /**
     * Finds a student with their courses loaded in one query
     * (JOIN FETCH).
     */
    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = em.createQuery(
            "SELECT s FROM Student s "+
            "JOIN FETCH s.courses " + 
            "WHERE s.id=:data", Student.class); 
        query.setParameter("data", theId);

        Student student = query.getSingleResult();
        return student;
    }

    /**
     * Updates an existing student (merge).
     */
    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    /**
    * {@inheritDoc}
    * Removes the student from each course's list before deleting,
    * so the join table rows are cleaned up.
    */
    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student student = em.find(Student.class, theId);

        if(student !=null){
            List<Course> courses = student.getCourses();

            for(Course c: courses){
                c.getStudents().remove(student);
            }
        }
        em.remove(student);
    }
}
