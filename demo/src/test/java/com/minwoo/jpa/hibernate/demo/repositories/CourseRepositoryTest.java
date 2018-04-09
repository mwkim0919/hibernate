package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.DemoApplication;
import com.minwoo.jpa.hibernate.demo.entities.Course;
import com.minwoo.jpa.hibernate.demo.entities.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class}) // Launching the contexts that the specified classes contains
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	public void findByIdTest() {
		Course course = courseRepository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());
	}

	@Test
	@DirtiesContext // After this test has been run, Spring will automatically revert the data changes back!
	public void updateTest() {
		Course course = courseRepository.findById(10001L);
		course.setName("Minwoo");
		courseRepository.save(course);

		Course result = courseRepository.findById(10001L);
		assertEquals("Minwoo", result.getName());
	}

	@Test
	@DirtiesContext // After this test has been run, Spring will automatically revert the data changes back!
	public void deleteByIdTest() {
		courseRepository.deleteById(10002L);
		assertNull(courseRepository.findById(10002L));
	}

	@Test
	@DirtiesContext
	public void playWithEntityManagerTest() {
		courseRepository.playWithEntityManager();
	}

	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course = courseRepository.findById(10001L);
		logger.info("{}", course.getReviews());
	}

	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Review review = entityManager.find(Review.class, 50001L);
		logger.info("{}", review.getCourse());
	}

}
