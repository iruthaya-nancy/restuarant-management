package com.example.restaurant.service;

import com.example.restaurant.dto.OrderEmail;

public interface OrderMailService {
		public OrderEmail toSendMail(Long customerId,Long orderId);
}
