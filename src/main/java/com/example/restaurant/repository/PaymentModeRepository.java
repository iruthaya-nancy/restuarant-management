package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.Entity.PaymentMode;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode,Long> {

}
