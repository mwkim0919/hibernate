package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.DemoApplication;
import com.minwoo.jpa.hibernate.demo.entities.Course;
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
// Native queries are good for mass update or using a functionality which JPQL cannot support.
public class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager entityManager;

	@Test
	public void native_queries_basic() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE -> {}", resultList);
	}

	@Test
	public void native_queries_where() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = ?", Course.class);
		query.setParameter(1, 10001L);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE WHERE id = ? -> {}", resultList);
	}

	@Test
	public void native_queries_where2() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = :id", Course.class);
		query.setParameter("id", 10001L);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE WHERE id = ? -> {}", resultList);
	}
}
