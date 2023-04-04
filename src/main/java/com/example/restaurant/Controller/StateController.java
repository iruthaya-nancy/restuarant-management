package com.example.restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.restaurant.dto.StateDto;
import com.example.restaurant.service.serviceImpl.StateServiceImpl;

@RestController
public class StateController {
		@Autowired
		public StateServiceImpl stateService;
		
		@PostMapping("/state")
		public ResponseEntity<Void> createState(@RequestBody StateDto stateDtoImpl){
			stateService.insertState(stateDtoImpl);
			 return ResponseEntity.status(HttpStatus.CREATED).build();
			
		}
}
