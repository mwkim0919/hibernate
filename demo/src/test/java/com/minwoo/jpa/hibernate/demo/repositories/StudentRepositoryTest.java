package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.DemoApplication;
import com.minwoo.jpa.hibernate.demo.entities.Passport;
import com.minwoo.jpa.hibernate.demo.entities.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class}) // Launching the contexts that the specified classes contains
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
//	@Transactional // Persistence Context: keep track of all changes in all entities being managed.
	public void someTest() {
		studentRepository.someOperationToUnderstandPersistenceContext();
	}

	@Test
	@Transactional // Without this, lazy getPassport will fail because the session ends as soon as student is found.
	public void retrieveStudentAndPassportDetails() {
		Student student = entityManager.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());
	}

	@Test
	@Transactional // Without this, lazy getPassport will fail because the session ends as soon as student is found.
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = entityManager.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("student -> {}", passport.getStudent());
	}

	@Test
	@Transactional
	public void retrieveStudentsAndCourses() {
		Student student = entityManager.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses());
	}

}
