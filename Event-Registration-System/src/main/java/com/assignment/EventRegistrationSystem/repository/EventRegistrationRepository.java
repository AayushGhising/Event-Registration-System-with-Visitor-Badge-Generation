package com.assignment.EventRegistrationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository; // This helps to perform CRUD operations
import org.springframework.stereotype.Repository;

import com.assignment.EventRegistrationSystem.entity.EventRegistration;

@Repository // This tells Spring to bootstrap the repository during component scan
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Integer>{
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
