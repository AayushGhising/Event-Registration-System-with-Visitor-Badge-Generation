package com.assignment.EventRegistrationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository; // This helps to perform CRUD operations
import org.springframework.stereotype.Repository;

import com.assignment.EventRegistrationSystem.entity.EventRegistration;

@Repository // This tells Spring to bootstrap the repository during component scan and helps to handle the data access

// This helps to perform CRUD operations on the EventRegistration entity and is made interface because it extends JpaRepository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Integer>{
    // boolean existsByEmail(String email); // This helps to check if the email already exists in the database
    // boolean existsByPhone(String phone); // This helps to check if the phone number already exists in the database
}
