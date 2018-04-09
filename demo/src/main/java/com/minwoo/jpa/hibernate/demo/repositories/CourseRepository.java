package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.entities.Course;
import com.minwoo.jpa.hibernate.demo.entities.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional // import the spring one
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    // Transactional makes this a single transaction
    public void playWithEntityManager() {
        Course course1 = new Course("Web Services in 100 Steps");
        entityManager.persist(course1); // this keeps track of all the changes
        entityManager.flush(); // changes done before this will be sent to DB
        // no need to call entityManager.merge(course) because of the persist method.
        course1.setName("Web Services in 100 Steps - Updated");

        entityManager.refresh(course1); // This will refresh the data for course1 by fetching the data in DB
        // course1 back to "Web Services in 100 Steps"
        entityManager.flush();

        Course course2 = new Course("AngularJS in 100 Steps");
        entityManager.persist(course2);
        entityManager.flush();

        entityManager.detach(course2); // course2 is no longer tracked by entityManager
        // Any changes after this won't be sent to DB.

        course2.setName("AngularJS in 100 Steps - Updated");
        entityManager.flush();

        entityManager.clear(); // this will clear out all the tracked entities

        Course course3 = findById(10001L);
        course3.setName("JPA in 50 steps - updated");
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("course.getReivews -> {}", course.getReviews());

        for (Review review : reviews) {
            course.addReview(review);
            review.setCourse(course);
            entityManager.persist(review);
        }
    }
}
