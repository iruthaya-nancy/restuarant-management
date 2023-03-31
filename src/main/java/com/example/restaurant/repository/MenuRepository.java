package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
	List<Menu> findByIdIn(List<Long> menuItemIds);
}
