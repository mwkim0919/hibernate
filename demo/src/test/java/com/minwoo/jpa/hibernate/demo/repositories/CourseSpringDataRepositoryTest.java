package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.DemoApplication;
import com.minwoo.jpa.hibernate.demo.entities.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseSpringDataRepository courseSpringDataRepository;

    @Test
    public void findById_CoursePresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playingWithSpringDataRepository() {
//        Course course = new Course("Microservices in 100 Steps");
//        courseSpringDataRepository.save(course);

//        course.setName("Microservices in 100 Steps - updated");
//        courseSpringDataRepository.save(course);
        Sort sort = new Sort(Sort.Direction.DESC, "name").and(new Sort(Sort.Direction.ASC, "name"));
        logger.info("Courses -> {}", courseSpringDataRepository.findAll(sort));
        logger.info("Count -> {}", courseSpringDataRepository.count());
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
        logger.info("First Page -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();

        Page<Course> secondPage = courseSpringDataRepository.findAll(secondPageable);
        logger.info("Second Page -> {}", secondPage.getContent());
    }

    @Test
    public void findUsingName() {
        logger.info("FindByName -> {}", courseSpringDataRepository.findByName("JPA in 50 steps"));
    }
}
