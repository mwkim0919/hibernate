package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.DemoApplication;
import com.minwoo.jpa.hibernate.demo.entities.Course;
import com.minwoo.jpa.hibernate.demo.entities.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class}) // Launching the contexts that the specified classes contains
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager entityManager;

	@Test
	public void jpql_basic() {
		// Course here represents Entity not Table in DB
		List resultList = entityManager.createQuery("Select c From Course c").getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_named_query() {
		// Course here represents Entity not Table in DB
		Query query = entityManager.createNamedQuery("query_get_all_courses");
		List resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpqa_typed() {
//		TypedQuery<Course> query = entityManager.createQuery("Select c From Course c", Course.class);
		TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpqa_where() {
//		TypedQuery<Course> query = entityManager.createQuery("Select c From Course c where name like '%100 steps'", Course.class);
		TypedQuery<Course> query = entityManager.createNamedQuery("query_get_100_step_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c where name like '%100 steps' -> {}", resultList);
	}

	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_courses_with_at_least_2_students() {
		TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query = entityManager.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = entityManager.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	//like
	//BETWEEN 100 and 1000
	//IS NULL
	//upper, lower, trim, length

	/**
	 * JOIN			=> Select c, s from Course c JOIN c.students s
	 * LEFT JOIN	=> Select c, s from Course c LEFT JOIN c.students s
	 * CROSS JOIN	=> Select c, s from Course c, Student s
	 */
	@Test
	public void join() {
		Query query = entityManager.createQuery("Select c, s from Course c JOIN c.students s");
		// The above query will return an array containing array of courses and array of students
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for (Object[] result : resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	public void left_join() {
		Query query = entityManager.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		// The above query will return an array containing array of courses and array of students
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for (Object[] result : resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	public void cross_join() {
		Query query = entityManager.createQuery("Select c, s from Course c, Student s");
		// The above query will return an array containing array of courses and array of students
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for (Object[] result : resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
}
