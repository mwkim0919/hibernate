package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="/courses") // providing RESTful services for this repository
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    // All courses that contain name
    List<Course> findByName(String name);

    List<Course> findByNameAndId(String name, Long id);

    List<Course> findByNameOrderById(String name);

    List<Course> findByNameOrderByIdDesc(String name);

    List<Course> deleteByName(String name);

    @Query("Select c From Course c Where c.name like '%100 Steps'")
    List<Course> coursesWith100Steps();

    @Query(value = "Select * From Course Where name like '%100 Steps'", nativeQuery = true)
    List<Course> coursesWith100StepsNativeQuery();

    @Query(name = "query_get_all_courses")
    List<Course> coursesWith100StepsNamedQuery();
}
