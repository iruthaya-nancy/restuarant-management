package com.example.restaurant.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.el.parser.AstDotSuffix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.SelectedFood;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.SelectedFoodRepository;
import com.example.restaurant.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {
    
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private SelectedFoodRepository selectedFoodRepository;
	

	/* public Optional<Order> getOrderById(Long orderId) {
	        return order.findById(orderId);
	    }
	*/
	@Transactional
	public void createOrder(List<SelectedFoodDto> selectedFoods, Long cutomerId) throws Exception {
//		Customer customer = new Customer();
//		Order order = new Order();
//		order.setId(orderDto.getId());
//	    order.setCustomer(new Customer(customer.getId()));
//	    order.setTotalAmount(orderDto.getTotalAmount());
//	    orderRepo.save(order);
		if(!CollectionUtils.isEmpty(selectedFoods)) {
			List<Long> id = selectedFoods.stream().map(SelectedFoodDto::getMenuId)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
			List<Menu> menus = menuRepository.findByIdInAndIsActiveIsTrue(id);
			if(!CollectionUtils.isEmpty(menus)) {
				if(menus.size() != selectedFoods.size()) {
					throw new Exception("Selected menu id not available");
				}
				Order order = new Order();
				order.setTotalAmount(calculateTotal(selectedFoods, menus));
				order.setCustomer(new Customer(cutomerId));
				orderRepo.save(order);
				List<SelectedFood> sf = new ArrayList<>();
				selectedFoods.forEach(food ->{
				SelectedFood se = new SelectedFood();
				se.setMenu(new Menu(food.getMenuId()));
				se.setQuantity(food.getQuantity());
				se.setOrder(order);
				sf.add(se);
				});
				selectedFoodRepository.saveAll(sf);
			}
		}
		
	    
	}

	private Double calculateTotal(List<SelectedFoodDto> selectedFoods, List<Menu> menus) {
		// check the id from menu and selected food
	        	Double total = null;
	    		for(SelectedFoodDto sdto:selectedFoods) 
	    		{
	    			Optional<Menu> optionalMenu = menuRepository.findById(sdto.getId());
	    			if(!optionalMenu.isEmpty()) {
	    				Menu m = optionalMenu.get();
	    			    total = sdto.getQuantity().doubleValue()*m.getAmount().doubleValue();
	    	}
	   	}
				return total;
	 }
		
}
