package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.Entity.SelectedFood;

@Repository
public interface SelectedFoodRepository extends JpaRepository<SelectedFood,Long> {

}
