package com.example.restaurant.service.impl;



import java.math.BigDecimal;

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
		
	    //Menu m = new Menu();
		
		public void addItemToOrder(Long orderId, Long foodId,Long quantity ) {
			Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
			Menu food = menu.findById(foodId).orElseThrow(EntityNotFoundException::new);
			SelectedFood sf = new SelectedFood();
			//sf.setMenu(food);
			sf.setOrder(order);
			sf.setQuantity(quantity);// quantity is the user parameter 
			//sf.setQuantityAmount(sf.getQuantity().multiply(m.getAmount()));
			selectedFood.save(sf);
		}// quantity is the user parameter 
		
		
}
