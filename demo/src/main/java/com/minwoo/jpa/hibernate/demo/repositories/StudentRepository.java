package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.entities.Passport;
import com.minwoo.jpa.hibernate.demo.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
        return student;
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        entityManager.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        entityManager.persist(student);
    }

    public void someOperationToUnderstandPersistenceContext() {
        Student student = entityManager.find(Student.class, 20001L);
        // Persistence Context (student)
        Passport passport = student.getPassport();
        // Persistence Context (student, passport)
        passport.setNumber("K123456");
        // Persistence Context (student, passport++)
        student.setName("Ranga - updated");
        // Persistence Context (student++, passport++)
    }
}
