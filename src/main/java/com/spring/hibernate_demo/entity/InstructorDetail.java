package com.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents instructor details mapped to 
 * the "instructor_detail" table.
 * Linked one-to-one with {@link Instructor}.
 */
@Entity 
@Table(name = "instructor_detail")
public class InstructorDetail {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id") 
    private int id;

    @Column(name="subject")
    private String subject;

    @Column(name="hobby")
    private String hobby;

    @OneToOne(mappedBy="instructorDeets", 
              cascade= {CascadeType.DETACH,
                        CascadeType.MERGE, 
                        CascadeType.PERSIST, 
                        CascadeType.REFRESH})
    private Instructor instructor;
    
    public InstructorDetail(){
    }

    /**
     * Gets the detail id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the detail id.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the instructor's subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the instructor's subject.
     *
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the instructor's hobby.
     *
     * @return the hobby
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * Sets the instructor's hobby.
     *
     * @param hobby the hobby to set
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * Gets the instructor linked to this detail.
     *
     * @return the instructor
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor linked to this detail.
     *
     * @param instructor the instructor to set
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Creates an instructor detail without an 
     * id (generated on save).
     *
     * @param subject the subject
     * @param hobby   the hobby
     */
    public InstructorDetail(String subject, String hobby){
        this.subject= subject;
        this.hobby = hobby;
    }

    /**
     * Returns a string representation of the instructor detail.
     *
     * @return a formatted string with the detail values
     */
    @Override 
    public String toString(){
        return "Instructor Details: [id=" + id + 
        ", subject = " +subject+
        ", hobby = " +hobby+ "]";
    }
}
