package com.example.restaurant.service.orderService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.PaymentMode;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.PaymentModeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl {
    
	@Autowired
	private OrderRepository order;
	
	@Autowired
	private CustomerRepository customer;
	
	@Autowired
	private PaymentModeRepository payment;
	
	 public Optional<Order> getOrderById(Long orderId) {
	        return order.findById(orderId);
	    }
	
	public void addOrder(Long customerId, Double Total, Long modeid) {
		Customer cust = customer.findById(customerId).orElseThrow(EntityNotFoundException::new);
		PaymentMode mode = payment.findById(modeid).orElseThrow(EntityNotFoundException::new);
		//Total = is the sum of selected food
		
	}
}
