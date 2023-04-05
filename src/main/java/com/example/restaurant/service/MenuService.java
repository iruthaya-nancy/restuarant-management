package com.example.restaurant.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.example.restaurant.dto.FoodSoldDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;

public interface MenuService {
	
	public void toInsertFoodToMenu(MenuDto menuDto) throws  ContraintViolationException;
	public void toDeleteFoodFromMenu(Long id) throws BusinessServiceException;
	public void toUpdateTheFood(Long id,BigDecimal price) throws BusinessServiceException;
	public List<FoodSoldDto> toGetTheFoodSold(Date fromDate,Date toDate)throws BusinessServiceException;

}
