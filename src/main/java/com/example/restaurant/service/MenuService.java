package com.example.restaurant.service;

import java.math.BigDecimal;

import com.example.restaurant.dto.MenuDto;

public interface MenuService {
	
	public void toInsertFoodToMenu(MenuDto menuDto);
	public void toDeleteFoodFromMenu(Long id);
	public void toUpdateTheFood(Long id,BigDecimal price);

}
