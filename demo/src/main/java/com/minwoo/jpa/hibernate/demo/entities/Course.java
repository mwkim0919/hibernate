package com.minwoo.jpa.hibernate.demo.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Course") // maps to table in DB by name.
// if name was "CourseDetails", then it maps to "course_details" table
@NamedQueries(
        value = {
                @NamedQuery(name = "query_get_all_courses", query="Select c From Course c"),
                @NamedQuery(name = "query_get_100_step_courses", query="Select c From Course c where name like '%100 steps'")
        }
)
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @UpdateTimestamp // Hibernate will update this column whenever a row has been updated.
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp // Hibernate will update this column whenever a row has been inserted.
    private LocalDateTime createdDate;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
