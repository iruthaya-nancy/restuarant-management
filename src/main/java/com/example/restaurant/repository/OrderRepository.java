package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.Entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

}
