package com.minwoo.jpa.hibernate.demo.entities;

import javax.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport") // mappedBy the name of variable in reference entity
    private Student student;

    protected Passport() {
    }

    public Passport(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Passport{");
        sb.append("id=").append(id);
        sb.append(", number='").append(number).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
