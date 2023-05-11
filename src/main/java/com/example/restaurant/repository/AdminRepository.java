package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByEmailAndPassword(String email, String password);
}
