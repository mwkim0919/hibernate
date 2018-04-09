package com.minwoo.jpa.hibernate.demo.entities;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String rating;

    private String description;

    @ManyToOne
    private Course course;

    protected Review() {
    }

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Review{");
        sb.append("id=").append(id);
        sb.append(", rating='").append(rating).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
