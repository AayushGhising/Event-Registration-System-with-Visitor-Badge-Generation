package com.assignment.EventRegistrationSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity // This helps to map the class to the postgreSQL database
@Table(name = "users") // This connects the class to the table in the database named users
@Getter // This helps to generate getters for all the fields
@Setter // This helps to generate setters for all the fields


// This class helps to create the table in the database
public class EventRegistration {
    @Id // This helps to specify the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This helps to generate the primary key automatically
    private int id;

    @Column(nullable = false) // This helps to specify the column in the table and it cannot be null
    private String full_name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String photo_path;  

}
