package com.minwoo.jpa.hibernate.demo;

import com.minwoo.jpa.hibernate.demo.entities.*;
import com.minwoo.jpa.hibernate.demo.repositories.CourseRepository;
import com.minwoo.jpa.hibernate.demo.repositories.EmployeeRepository;
import com.minwoo.jpa.hibernate.demo.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Student student = new Student("Jack");
//		Course course = new Course("Microservices in 100 Steps");
//		studentRepository.insertStudentAndCourse(student, course);
		Employee employee1 = new FullTimeEmployee("Jack", new BigDecimal(10000));
		Employee employee2 = new PartTimeEmployee("Jill", new BigDecimal(50));
		employeeRepository.insert(employee1);
		employeeRepository.insert(employee2);

		logger.info("All employees -> {}", employeeRepository.retrieveAllEmployees());
	}
}
