package com.example.restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.service.serviceImpl.OrderServiceImpl;

@RestController
public class OrderController {  
	@Autowired
	public OrderServiceImpl orderService;
	
	@PostMapping("/order")
	public ResponseEntity<Void> toCreateOrder(@RequestBody OrderDto orderDto){
		orderService.createOrder(orderDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
