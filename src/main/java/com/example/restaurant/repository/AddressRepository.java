package com.example.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.Area;


@Repository
public interface AddressRepository extends JpaRepository<Area,Long> {

	Optional<Area> findByPincode(Long pincode);

}
