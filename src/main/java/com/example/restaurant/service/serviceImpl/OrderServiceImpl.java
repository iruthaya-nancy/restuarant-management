package com.example.restaurant.service.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Order;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {
    
	@Autowired
	private OrderRepository orderRepo;
	

	/* public Optional<Order> getOrderById(Long orderId) {
	        return order.findById(orderId);
	    }
	*/
	@Transactional
	public void createOrder(OrderDto orderDto) {
		Customer customer = new Customer();
		Order order = new Order();
		order.setId(orderDto.getId());
	    order.setCustomer(new Customer(customer.getId()));
	    order.setTotalAmount(orderDto.getTotalAmount());
	    orderRepo.save(order);
	}
}
