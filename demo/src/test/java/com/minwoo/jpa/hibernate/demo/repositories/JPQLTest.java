package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.DemoApplication;
import com.minwoo.jpa.hibernate.demo.entities.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
}
