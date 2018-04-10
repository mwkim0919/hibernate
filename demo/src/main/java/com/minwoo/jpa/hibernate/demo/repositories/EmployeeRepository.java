package com.minwoo.jpa.hibernate.demo.repositories;

import com.minwoo.jpa.hibernate.demo.entities.Employee;
import com.minwoo.jpa.hibernate.demo.entities.FullTimeEmployee;
import com.minwoo.jpa.hibernate.demo.entities.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    public List<Employee> retrieveAllEmployees() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    // This has to be used when using @MappedSuperclass
//    public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
//        return entityManager.createQuery("SELECT e FROM PartTimeEmployee e", PartTimeEmployee.class).getResultList();
//    }

    // This has to be used when using @MappedSuperclass
//    public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
//        return entityManager.createQuery("SELECT e FROM FullTimeEmployee e", FullTimeEmployee.class).getResultList();
//    }

    // INSERT OR UPDATE
    public void insert(Employee employee) {
        entityManager.persist(employee);
    }
}
