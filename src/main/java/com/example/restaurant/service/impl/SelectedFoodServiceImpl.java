package com.example.restaurant.service.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.SelectedFood;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.SelectedFoodRepository;
import com.example.restaurant.service.SelectedFoodService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SelectedFoodServiceImpl implements SelectedFoodService {

		@Autowired
		private MenuRepository menu;
		
		@Autowired
		private SelectedFoodRepository selectedFood;
		
		@Autowired
		private OrderRepository orderRepository;
		
		
		public void addItemToOrder(Long orderId, Long foodId,Long quantity ) {
			Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
			Menu food = menu.findById(foodId).orElseThrow(EntityNotFoundException::new);
			SelectedFood sf = new SelectedFood();
			sf.setOrder(order);
			sf.setQuantity(quantity);// quantity is the user parameter 
			selectedFood.save(sf);
		}
		
		
}
