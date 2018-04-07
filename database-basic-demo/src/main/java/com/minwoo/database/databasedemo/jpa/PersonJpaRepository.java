package com.minwoo.database.databasedemo.jpa;

import com.minwoo.database.databasedemo.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
// Transactional
// Important when you are doing multiple steps (you want all of them to be successful or to fail
// Ideally, Transaction is implemented around business logics
@Transactional
public class PersonJpaRepository {

    // connect to the database
    @PersistenceContext
    private EntityManager entityManager;

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person update(Person person) {
        return entityManager.merge(person); // merge check whether id has been set for person or not
    }

    public Person insert(Person person) {
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        Person person = findById(id);
        entityManager.remove(person);
    }

    public List<Person> findAll() {
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
        return namedQuery.getResultList();
    }

}
