package com.minwoo.jpa.hibernate.demo;

import com.minwoo.jpa.hibernate.demo.entities.Course;
import com.minwoo.jpa.hibernate.demo.entities.Review;
import com.minwoo.jpa.hibernate.demo.entities.Student;
import com.minwoo.jpa.hibernate.demo.repositories.CourseRepository;
import com.minwoo.jpa.hibernate.demo.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

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
		Student student = new Student("Jack");
		Course course = new Course("Microservices in 100 Steps");
		studentRepository.insertStudentAndCourse(student, course);
	}
}
