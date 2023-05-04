package com.example.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.dto.AdminDto;
import com.example.restaurant.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
	Admin findByFirstNameAndPassword(String firstName,String password);
}
