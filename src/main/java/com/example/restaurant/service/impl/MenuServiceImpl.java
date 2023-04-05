package com.example.restaurant.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.FoodSoldDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;
import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuRepository menuRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	public void toInsertFoodToMenu(MenuDto menudto) throws ContraintViolationException{
	 if(menudto != null) {
		Menu menu = this.modelMapper.map(menudto, Menu.class);
		menuRepo.save(menu);
	 }
	 else {
		 throw new ContraintViolationException("Please enter the proper data");
	 }
	 
	}
	
	@Transactional
	public void toDeleteFoodFromMenu(Long id) throws BusinessServiceException
	{
		if(menuRepo.existsById(id))
		{
			menuRepo.deleteById(id);
		}
		else {
			throw new BusinessServiceException("Please enter the proper data");
		}
	}
	
	@Transactional 
	public void toUpdateTheFood(Long id,BigDecimal price) throws BusinessServiceException
	{
		Menu updateMenu = menuRepo.findById(id).get();
		if(updateMenu != null) {
			updateMenu.setAmount(price);
			menuRepo.save(updateMenu);
		}
		else {
			throw new BusinessServiceException("Please enter the proper data");
		}
		
	}
	//conversion to dto is done in controller
	//List<MenuDto> menu = (List<MenuDto>) this.modelMapper.map(menuDto, Menu.class);
	// TODO Auto-generated method stub
	public List<Menu> toViewTheMenu(int page,int size) throws BusinessServiceException
	{
		Pageable paging = PageRequest.of(page, size);
		Page<Menu> menuItem = menuRepo.findAll(paging);
		if(menuItem!=null) {
			return menuItem.getContent();
		}
		else {
			throw new BusinessServiceException("Please enter the proper data");
		}
	}
	
	public List<FoodSoldDto> toGetTheFoodSold(Date fromDate,Date toDate) throws BusinessServiceException
	{
	List<FoodSoldDto> foodSoldDto = menuRepo.findFoodSold(fromDate, toDate);
			//.stream().map(e ->{
//			FoodSoldDto food = new FoodSoldDto();
//			food.setName(e.setFoodName());
//			food.setCount(e.setFoodCount());
//			return food;
//		}).collect(Collectors.toList());
//		
		if(foodSoldDto != null) {
			           return foodSoldDto;
		}
		else {
			throw new BusinessServiceException("No foods were sold on the mentioned date");
		}
		
	}
	
	    
	
}
