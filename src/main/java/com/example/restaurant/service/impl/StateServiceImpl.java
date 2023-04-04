package com.example.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.StateDto;
import com.example.restaurant.model.State;
import com.example.restaurant.repository.StateRepository;

// insert and delete state
@Service
public class StateServiceImpl {
	
	@Autowired
	public StateRepository stateRepo;

	@Transactional
	public void insertState(StateDto stateDtoImpl) {
		State s = new State();
		s.setId(stateDtoImpl.getId());
		s.setName(stateDtoImpl.getName());
		s.setIsActive(stateDtoImpl.getIsActive());
		stateRepo.save(s);
		
	}

}
