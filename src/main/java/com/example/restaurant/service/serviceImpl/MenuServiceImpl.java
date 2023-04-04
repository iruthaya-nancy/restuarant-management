package com.example.restaurant.service.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	public MenuRepository menuRepo;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Transactional
	public void toInsertFoodToMenu(MenuDto menudto) {
		Menu menu = this.modelMapper.map(menudto, Menu.class);
		menuRepo.save(menu);
	}
	
	@Transactional
	public void toDeleteFoodFromMenu(Long id) {
		if(menuRepo.existsById(id)) {
			menuRepo.deleteById(id);
		}
	}
	
	@Transactional 
	public void toUpdateTheFood(Long id,BigDecimal price) {
		//Menu menu = this.modelMapper.map(menudto,Menu.class);
		Menu updateMenu = menuRepo.findById(id).get();
		updateMenu.setAmount(price);
		menuRepo.save(updateMenu);
		
	}
   //conversion to dto is done in controller
	public List<Menu> toViewTheMenu(int page,int size) {
		Pageable paging = PageRequest.of(page, size);
		//List<MenuDto> menu = (List<MenuDto>) this.modelMapper.map(menuDto, Menu.class);
		// TODO Auto-generated method stub
		Page<Menu> menuItem = menuRepo.findAll(paging);
		return menuItem.getContent();
	}
	
	    
	
}
