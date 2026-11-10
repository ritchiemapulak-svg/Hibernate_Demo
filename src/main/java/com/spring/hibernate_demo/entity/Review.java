package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a course review mapped to the "review" table.
 */
@Entity 
@Table(name="review")
public class Review {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="comments")
    private String comments;

    public Review(){
    }

    /**
     * Creates a review with the given comment.
     *
     * @param comments the review text
     */
    public Review(String comments){
        this.comments = comments;
    }

    /**
     * Gets the review id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the review id.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the review comment.
     *
     * @return the comment text
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the review comment.
     *
     * @param comments the comment text to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Returns a string representation of the review.
     *
     * @return a formatted string with the review details
     */
    @Override
    public String toString() {
        return "Review [id=" + id + ", comments=" + comments + "]";
    }
}
