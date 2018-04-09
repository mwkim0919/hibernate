package com.minwoo.jpa.hibernate.demo;

import com.minwoo.jpa.hibernate.demo.entities.Course;
import com.minwoo.jpa.hibernate.demo.repositories.CourseRepository;
import com.minwoo.jpa.hibernate.demo.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = courseRepository.findById(10001L);
		logger.info("{}", course);

		courseRepository.save(new Course("Spring in 100 steps"));
		courseRepository.playWithEntityManager();

		studentRepository.saveStudentWithPassport();
	}
}
