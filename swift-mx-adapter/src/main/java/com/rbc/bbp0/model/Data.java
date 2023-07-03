package com.rbc.bbp0.model;


import jakarta.persistence.*;

@Entity
@Table(name = "data_table")
@lombok.Data
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountNumber")
    private Long accountNumber;

    @Column(name = "frcNumber")
    private Long frcNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    // Constructors, getters, setters, etc.
}
