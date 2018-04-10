package com.minwoo.jpa.hibernate.demo.entities;

import javax.persistence.*;

@Entity
/**
 * Options
 * 1. **SINGLE_TABLE: store PartTime and FullTime in a single table (Data entry is not good)
 * 2. TABLE_PER_CLASS: create a table for each of concrete type: PartTime & FullTime (design-wise may not be good. too many duplicates)
 *                      NOT REALLY GOOD
 * 3. **JOINED: (may not be good for performance but it is a good design!!!)
 *              A strategy in which fields that are specific to a
 *              subclass are mapped to a separate table than the fields
 *              that are common to the parent class, and a join is
 *              performed to instantiate the subclass.
 * 4. @MappedSuperClass: mapping only applies to subclasses (have to write separate queries for subclasses)
 *                      NOT REALLY GOOD
 */
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EmployeeType") // This will change the name of discriminator column for Employee
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    protected Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
