package com.example.restaurant.Controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.model.Menu;
import com.example.restaurant.service.impl.MenuServiceImpl;

@RestController
public class MenuController {

	@Autowired
	public MenuServiceImpl menuService;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@PostMapping("/addmenu")
	public ResponseEntity<String> toInsertFoodToMenu(@RequestBody MenuDto menuDto){
			menuService.toInsertFoodToMenu(menuDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
	}
	
	@DeleteMapping("/deletmenu")
	public ResponseEntity<String> toDeleteFoodFromMenu(@RequestParam("id") Long id){
		menuService.toDeleteFoodFromMenu(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted succsfully");
	}
	
	@PutMapping("/updatecost")
	public ResponseEntity<String> toUpdateTheFood(@RequestParam("id") Long id,@RequestParam("price") BigDecimal price){
		menuService.toUpdateTheFood(id,price);
		return ResponseEntity.status(HttpStatus.OK).body("Updated Successfully");
	}
	
	@GetMapping("/viewmenu")
	public List<MenuDto> toViewTheMenu(@RequestParam(value = "page")int page,@RequestParam(value = "size") int size){
		List<Menu> menu = menuService.toViewTheMenu(page,size);
		return menu.stream()
		          .map(this::convertToDto)
		          .collect(Collectors.toList());
	}
	private MenuDto convertToDto(Menu menu) {
		MenuDto menudto = modelMapper.map(menu,MenuDto.class);
		menudto.setId(menu.getId());
		menudto.setName(menu.getName());
		menudto.setDescription(menu.getDescription());
		menudto.setAmount(menu.getAmount());
		menudto.setIsActive(menu.getIsActive());
		return menudto;
	}
	
	//@GetMapping("/")
	//public List<FoodSoldDto> toGetCountOfFood(@RequestParam(value = "fromDate") Date fromDate,@RequestParam(value = "toDate")Date toDate){
		//menuService.toGetTheFoodCount(fromDate,toDate,name);// do i have to pass the name
	//}
	
		
	

}
