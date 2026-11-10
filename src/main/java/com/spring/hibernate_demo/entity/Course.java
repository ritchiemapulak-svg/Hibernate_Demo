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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a course mapped to the "course" table.
 * Has one instructor, many reviews, and many students.
 */
@Entity 
@Table(name="course")
public class Course {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH,
                          CascadeType.MERGE,
                          CascadeType.PERSIST,
                          CascadeType.REFRESH })

    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    
    public Course(){
    }

    /**
    * Creates a course with the given title.
    *
    * @param title the course title
    */
    public Course(String title){
        this.title = title;
    }

    /**
    * Gets the course id.
    *
    * @return the id
    */
    public int getId() {
        return id;
    }

    /**
    * Sets the course id.
    *
    * @param id the id to set
    */
    public void setId(int id) {
        this.id = id;
    }

    /**
    * Gets the course title.
    *
    * @return the title
    */
    public String getTitle() {
        return title;
    }

    /**
    * Sets the course title.
    *
    * @param title the title to set
    */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
    * Gets the instructor teaching this course.
    *
    * @return the instructor
    */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
    * Sets the instructor teaching this course.
    *
    * @param instructor the instructor to set
    */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
    * Returns a string representation of the course.
    *
    * @return a formatted string with the course details
    */
    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title +
               ", instructor=" + instructor + "]";
    }

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name="course_id")
    private List<Review> review;
    
    /**
    * Adds a review to this course.
    *
    * @param rev the review to add
    */
    public void addReview(Review rev){
        if(review== null){
            review = new ArrayList<>();
        }
        review.add(rev);
    }

    /**
    * Gets the reviews of this course.
    *
    * @return the list of reviews
    */
    public List<Review> getReview() {
        return review;
    }

    /**
    * Sets the reviews of this course.
    *
    * @param review the list of reviews to set
    */
    public void setReview(List<Review> review) {
        this.review = review;
    }

    @ManyToMany(fetch = FetchType.LAZY, 
                cascade = {CascadeType.DETACH,
                          CascadeType.MERGE,
                          CascadeType.PERSIST,
                          CascadeType.REFRESH })

    @JoinTable(name="course_student", 
                joinColumns=@JoinColumn(name="course_id"),
                inverseJoinColumns=@JoinColumn(name="student_id"))
    private List <Student> students;
    
    /**
    * Adds a student to this course.
    *
    * @param stud the student to add
    */
    public void addStudent(Student stud){
        if(students == null){
            students = new ArrayList<>();
        }
        students.add(stud);
    }

    /**
    * Gets the students enrolled in this course.
    *
    * @return the list of students
    */
    public List<Student> getStudents() {
        return students;
    }

    /**
    * Sets the students enrolled in this course.
    *
    * @param students the list of students to set
    */
    public void setStudents(List<Student> students) {
        this.students = students;
    }
        
}
