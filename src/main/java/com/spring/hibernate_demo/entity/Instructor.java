package com.spring.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents an instructor mapped to the "instructor" table.
 * Has one {@link InstructorDetail} and many {@link Course}.
 */
@Entity 
@Table(name = "instructor")
public class Instructor {
 
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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDeets;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY,
               cascade = {CascadeType.DETACH, 
                          CascadeType.MERGE, 
                          CascadeType.PERSIST, 
                          CascadeType.REFRESH})
                          
    private List<Course> courses;

    public Instructor(){
    }

    /**
    * Creates an instructor without an id (generated on save).
    *
    * @param firstName the first name
    * @param lastName  the last name
    * @param email     the email address
    */
    public Instructor(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;    
    }

    /**
    * Gets the instructor id.
    *
    * @return the id
    */
    public int getId() {
        return id;
    }

    /**
    * Sets the instructor id.
    *
    * @param id the id to set
    */
    public void setId(int id) {
        this.id = id;
    }

    /**
    * Gets the first name.
    *
    * @return the first name
    */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the instructor's detail record.
     *
     * @return the instructor detail
     */
    public InstructorDetail getInstructorDeets() {
        return instructorDeets;
    }

    /**
     * Sets the instructor's detail record.
     *
     * @param instructorDeets the instructor detail to set
     */
    public void setInstructorDeets(InstructorDetail instructorDeets) {
        this.instructorDeets = instructorDeets;
    }

    /**
     * Adds a course to this instructor and sets 
     * this instructor on the course.
     *
     * @param tcourse the course to add
     */
    public void add (Course tcourse){

        if(this.courses == null) {
            this.courses = new ArrayList<>();
        }
        
        this.courses.add(tcourse);

        tcourse.setInstructor(this);
        
    }

    /**
     * Returns a string representation of the instructor.
     *
     * @return a formatted string with the instructor details
     */
    @Override 
    public String toString(){
        return "Instructor: [id=" + id + 
        ", firstName = " +firstName+
        ", lastName = " +lastName+
        ", email = " +email+ 
        ", instructorDetails= " + instructorDeets+ "]";
    }

    /**
     * Gets the courses taught by this instructor.
     *
     * @return the list of courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the courses taught by this instructor.
     *
     * @param courses the list of courses to set
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
