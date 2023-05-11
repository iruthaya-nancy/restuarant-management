package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.restaurant.model.SelectedFood;

@Repository
public interface SelectedFoodRepository extends JpaRepository<SelectedFood,Long> {
	 List<SelectedFood> findByOrderId(Long id);
}
