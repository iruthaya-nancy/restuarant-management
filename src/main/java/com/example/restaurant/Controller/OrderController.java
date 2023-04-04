package com.example.restaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.service.impl.OrderServiceImpl;

@RestController
public class OrderController {  
	@Autowired
	public OrderServiceImpl orderService;
	
	@PostMapping("/order")
	public ResponseEntity<Void> toCreateOrder(@RequestBody List<SelectedFoodDto> selectedFoods,@RequestParam(value = "id")Long id){
		try {
		    orderService.createOrder(selectedFoods,id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		    return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
