package com.example.restaurant.service.selectedFoodService;

import java.math.BigDecimal;

public interface SelectedFoodService {
	
	public void addItemToOrder(Long orderId, Long foodId,Long quantity );

}
