package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.Entity.District;


@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {

}
