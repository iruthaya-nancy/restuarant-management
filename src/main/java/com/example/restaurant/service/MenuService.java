package com.example.restaurant.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.example.restaurant.exception.ConstraintViolationException;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.dto.FoodSoldDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.exception.BusinessServiceException;

public interface MenuService {
	
	public void toInsertFoodToMenu(MenuDto menuDto) throws  ConstraintViolationException;
	public void toDeleteFoodFromMenu(Long id) throws BusinessServiceException;
	public void toUpdateTheFood(Long id,BigDecimal price) throws BusinessServiceException,NotFoundException;
	public List<FoodSoldDto> toGetTheFoodSold(Date fromDate,Date toDate)throws BusinessServiceException;

}
