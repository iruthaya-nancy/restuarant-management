package com.example.restaurant.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.restaurant.dto.FoodSoldDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ConstraintViolationException;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuRepository menuRepo;
	
		
	@Transactional
	public void toInsertFoodToMenu(MenuDto menudto) throws ConstraintViolationException{
	 if(menudto != null) {
		Menu menu = new Menu();
		menu.setId(menudto.getId());
		menu.setName(menudto.getName());
		menu.setDescription(menudto.getDescription());
		menu.setAmount(menudto.getAmount());
		menu.setIsActive(menudto.getIsActive());
		menuRepo.save(menu);
	 }
	 else {
		 throw new ConstraintViolationException("Please enter the proper data");
	 }
	 
	}
	
	@Transactional
	public void toDeleteFoodFromMenu(Long id) throws BusinessServiceException
	{
		if(menuRepo.existsById(id)){
			menuRepo.deleteById(id);
	}
	else {
		throw new BusinessServiceException("Id does not exists");
	}	
		
	}
	
	@Transactional 
	public void toUpdateTheFood(Long id,BigDecimal price) throws BusinessServiceException, NotFoundException
	{
		if(price != null) {
		Menu updateMenu = menuRepo.findById(id).get();
		if(updateMenu != null) {
			updateMenu.setAmount(price);
			menuRepo.save(updateMenu);
		}
		else {
			throw new BusinessServiceException("Please enter the proper data");
		}
		}
		else {
			boolean status;
			Menu menu = menuRepo.findById(id).get();
			if(menu!=null)
			
			{
		    if(menu.getIsActive() == true) {
		    	status = false;
		    	menu.setIsActive(status);
		    }
		    else {
		    	status = true;
		    	menu.setIsActive(status);
		    }
		    menuRepo.save(menu);
			}
			else {
				throw new NotFoundException("the food not available");
			}
		}
		
	}
	
	public List<Menu> toViewTheMenu() throws BusinessServiceException
	{
		
		List<Menu> menuItem = menuRepo.findAll();
		if(menuItem!=null) {
			return menuItem;
		}
		else {
			throw new BusinessServiceException("Please enter the proper data");
		}
	}
	
	public List<FoodSoldDto> toGetTheFoodSold(Date fromDate,Date toDate) throws BusinessServiceException
	{
	List<FoodSoldDto> foodSoldDto = menuRepo.findFoodSold(fromDate, toDate);
		
		if(foodSoldDto != null) {
			           return foodSoldDto;
		}
		else {
			throw new BusinessServiceException("No foods were sold on the mentioned date");
		}
		
	}
	
	    
	
}
