package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a Student entity mapped to the 
 * database table "student".
 */
@Entity 
@Table(name = "student")
public class Student {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id") 
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Student(){
    }

    /**
     * Parameterized constructor to create a student without an ID 
     * (ID is generated automatically upon persistence).
     *
     * @param firstName the student's first name
     * @param lastName  the student's last name
     * @param email     the student's email address
     */
    public Student(String firstName, String lastName, String email) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }

    /**
     * Gets the student's ID.
     *
     * @return the primary key ID of the student
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the student's ID.
     *
     * @param id the primary key ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the student's first name.
     *
     * @return the first name of the student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the student's first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the student's last name.
     *
     * @return the last name of the student
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the student's last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the student's email address.
     *
     * @return the email address of the student
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the Student entity object.
     *
     * @return a formatted string containing student details
     */
    @Override 
    public String toString(){
        return "Student [id=" + id + 
        ", firstName = " +firstName+
        ", lastName = " +lastName+
        ", email = " +email+ "]";
    }
    
}
