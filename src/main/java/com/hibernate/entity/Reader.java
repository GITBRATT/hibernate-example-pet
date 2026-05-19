package com.hibernate.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "reader")
    private List<Loan> loans = new ArrayList<>();

    public Reader() {
    }

    public Reader(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
