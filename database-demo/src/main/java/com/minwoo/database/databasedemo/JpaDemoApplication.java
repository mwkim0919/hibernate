package com.minwoo.database.databasedemo;

import com.minwoo.database.databasedemo.entity.Person;
import com.minwoo.database.databasedemo.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonJpaRepository personJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All users -> {}", personJpaRepository.findAll());
        logger.info("User id 10001 -> {}", personJpaRepository.findById(10001));
        personJpaRepository.deleteById(10001);
        logger.info("Inserting -> {}", personJpaRepository
                .insert(new Person("Minwoo2", "Vancouver", new Date())));
        logger.info("Updating 10003 -> {}", personJpaRepository
                .update(new Person(10003, "Minwoo3", "Berlin", new Date())));
    }
}
