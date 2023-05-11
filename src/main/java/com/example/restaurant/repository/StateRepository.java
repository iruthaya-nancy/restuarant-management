package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.State;


@Repository
public interface StateRepository extends JpaRepository<State,Long> {

}

