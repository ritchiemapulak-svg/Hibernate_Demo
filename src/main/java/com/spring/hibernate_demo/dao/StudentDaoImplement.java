package com.spring.hibernate_demo.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.spring.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * Provides database operations for 
 * managing {@link Student} entities.
 */
@Repository
public class StudentDaoImplement implements StudentDao{

    /**
     * The JPA entity manager used to interact with 
     * the persistence context.
     */
    private EntityManager em;

    /**
     * Constructs a new {@code StudentDaoImplement} instance 
     * with constructor injection.
     *
     * @param em the {@link EntityManager} injected by Spring
     */
    public StudentDaoImplement(EntityManager em){
        this.em = em;
    }

    /**
     * Saves a new student record to the database.
     *
     * @param theStudent the {@link Student} object to persist
     */
    @Override
    @Transactional 
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    /**
     * Finds a student by their unique ID.
     *
     * @param id the primary key ID of the student to search for
     * @return the matching {@link Student} entity
     */
    @Override
    public Student findbyId(Integer id) {
        return em.find(Student.class, id);
    }

    /**
     * Retrieves all student records ordered by their 
     * last name in ascending order.
     *
     * @return a {@link List} of all {@link Student} entities
     */
    @Override 
    public List<Student> findAll(){
        TypedQuery<Student> query= em.createQuery
        ("FROM Student order by lastName", Student.class);
        return query.getResultList();
    }

    /**
     * Finds all students matching a specific last name.
     *
     * @param lastName the last name to filter by
     * @return a {@link List} of matching {@link Student} entities
     */
    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = em.createQuery
        ("FROM Student WHERE lastName=:data", Student.class);
        query.setParameter("data", lastName);
        return query.getResultList();
    }

    /**
     * Updates an existing student record in the database.
     *
     * @param theStudent the {@link Student} object containing 
     * updated details
     */
    @Override
    @Transactional 
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    /**
     * Deletes a student record from the database by ID.
     *
     * @param id the primary key ID of the student to delete
     */
    @Override
    @Transactional 
    public void delete(Integer id) {
        Student student = em.find(Student.class, id);
        em.remove(student);
    }

    /**
     * Deletes all student records from the database.
     *
     * @return the number of student records deleted
     */
    @Override
    @Transactional 
    public int deleteAll() {
        int numRows = em.createQuery
        ("DELETE FROM Student").executeUpdate();
        return numRows;
    }
}
