package com.example.restaurant.service.SelectedFoodService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.Entity.Orders;
import com.example.restaurant.Entity.Menu;
import com.example.restaurant.Entity.SelectedFood;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.SelectedFoodRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SelectedFoodServiceImpl {

		@Autowired
		private MenuRepository menu;
		
		@Autowired
		private SelectedFoodRepository selectedFood;
		
		@Autowired
		private OrderRepository orderRepository;
		
		@Autowired
		private Menu m;
		
		public void addItemToOrder(Long orderId, Long foodId,int quantity ) {
			Orders order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
			Menu food = menu.findById(foodId).orElseThrow(EntityNotFoundException::new);
			SelectedFood sf = new SelectedFood();
			sf.setMenu(food);
			sf.setOrder(order);
			sf.setQuantity(quantity);// quantity is the user parameter 
			sf.setQuantityAmount(sf.getQuantity() * m.getAmount().doubleValue());
			selectedFood.save(sf);
		}// quantity is the user parameter 
		
		
}
