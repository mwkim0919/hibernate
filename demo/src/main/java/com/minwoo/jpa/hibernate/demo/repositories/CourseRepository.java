package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional // import the spring one
public class CourseRepository {

    @Autowired
    private EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    // INSERT OR UPDATE
    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course); // insert
        } else {
            entityManager.merge(course); // update
        }
        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void playWithEntityManager() {

    }
}
