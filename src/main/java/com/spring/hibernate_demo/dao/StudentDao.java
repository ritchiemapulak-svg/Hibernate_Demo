package com.spring.hibernate_demo.dao;

import java.util.List;

import com.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object (DAO) interface for 
 * performing CRUD operations 
 * on {@link Student} entities.
 */
public interface StudentDao {
    
    /**
     * Persists a new student entity to the database.
     *
     * @param theStudent the {@link Student} object to save
     */
    void save(Student theStudent);

    /**
     * Retrieves a student by their unique ID.
     *
     * @param id the primary key ID of the student
     * @return the matching {@link Student} entity,
     *  or {@code null} if not found
     */
    Student findbyId(Integer id);

    /**
     * Retrieves all student entities from the database.
     *
     * @return a {@link List} containing all students
     */
    List <Student> findAll();

    /**
     * Searches for students matching the specified last name.
     *
     * @param lastName the last name to filter by
     * @return a {@link List} of matching {@link Student} entities
     */
    List <Student> findByLastName(String lastName);

    /**
     * Updates an existing student record in the database.
     *
     * @param theStudent the {@link Student} object 
     * containing updated details
     */
    void update(Student theStudent);
    
    /**
     * Removes a student record from the database by ID.
     *
     * @param id the primary key ID of the student to remove
     */
    void delete(Integer id);

    /**
     * Removes all student records from the database.
     *
     * @return the total number of deleted rows
     */
    int deleteAll();
}
