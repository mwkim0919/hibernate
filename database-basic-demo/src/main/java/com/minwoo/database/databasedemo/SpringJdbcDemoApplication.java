package com.minwoo.database.databasedemo;

import com.minwoo.database.databasedemo.dao.PersonJdbcDao;
import com.minwoo.database.databasedemo.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonJdbcDao personJdbcDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", personJdbcDao.findAll());
		logger.info("User id 10001 -> {}", personJdbcDao.findById(10001));
		logger.info("Deleting 10002 -> No of Rows Deleted -> {}", personJdbcDao.deleteById(10002));
		logger.info("Inserting 10004 -> No of Rows Inserted -> {}", personJdbcDao
				.insert(new Person(10004, "Minwoo2", "Vancouver", new Date())));
		logger.info("Updating 10003 -> No of Rows Updated -> {}", personJdbcDao
				.update(new Person(10003, "Minwoo3", "Berlin", new Date())));
	}
}
