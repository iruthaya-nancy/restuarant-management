package com.example.restaurant.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.service.impl.OrderServiceImpl;
import com.example.restaurant.util.HttpStatusResponse;
import com.example.restaurant.util.ResponseUtils;

import jakarta.mail.MessagingException;

@RestController
public class OrderController {  
	@Autowired
	private OrderServiceImpl orderService;
	
	@PostMapping
	public ResponseEntity<HttpStatusResponse> toCreateOrder(@RequestBody List<SelectedFoodDto> selectedFoods,@RequestParam(value = "id")Long id,@RequestParam(value = "paymentid")Long paymentid) throws UnsupportedEncodingException, MessagingException{
		try {
		    orderService.createOrder(selectedFoods,id,paymentid);
		    return ResponseUtils.prepareAcceptedResponse("Order Confirmed", null);
		}
		catch(BusinessServiceException exception) {
					return ResponseUtils.prepareUnProcessableEntityResponse(
				"Failed to create Order .Please enter the suitable data");
		}
	}
}
