package com.minwoo.jpa.hibernate.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Entity
@Table(name = "Course") // maps to table in DB by name.
// if name was "CourseDetails", then it maps to "course_details" table
@NamedQueries(
        value = {
                @NamedQuery(name = "query_get_all_courses", query="Select c From Course c"),
                @NamedQuery(name = "query_get_100_step_courses", query="Select c From Course c where name like '%100 steps'")
        }
)
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id=?") // Soft delete annotation
@Where(clause = "is_deleted = false") // This will filter out any rows with is_deleted = false
public class Course {

    private static Logger LOGGER = Logger.getLogger(Course.class.getName());

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @UpdateTimestamp // Hibernate will update this column whenever a row has been updated.
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp // Hibernate will update this column whenever a row has been inserted.
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses") // mappedBy will fix two join tables problem
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    // for Soft delete
    private boolean isDeleted;

    @PreRemove // for Soft delete (JPA Life-cycle methods)
    private void preRemove() {
        LOGGER.info("Setting isDeleted to True");
        this.isDeleted = true;
    }

    protected Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    // Adding a single review to a course
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    // Remove a single review to a course
    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
