package com.example.restaurant.Controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.FoodSoldDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;
import com.example.restaurant.model.Menu;
import com.example.restaurant.service.impl.MenuServiceImpl;
import com.example.restaurant.util.HttpStatusResponse;
import com.example.restaurant.util.ResponseUtils;

//admin to access menu
@RequestMapping("/menu")
@RestController
public class MenuController {

	@Autowired
	public MenuServiceImpl menuService;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@PostMapping("/addmenu")
	public ResponseEntity<HttpStatusResponse> toInsertFoodToMenu(@RequestBody MenuDto menuDto)
	{
		try {
			menuService.toInsertFoodToMenu(menuDto);
			return ResponseUtils.prepareAcceptedResponse("Added Successfully", menuDto);
		}
		catch(ContraintViolationException exception) {
			return ResponseUtils.prepareUnProcessableEntityResponse("Failed to insert menu.Please enter the valid data");
		}
	}
	
	@DeleteMapping("/deletmenu")
	public ResponseEntity<HttpStatusResponse> toDeleteFoodFromMenu(@RequestParam("id") Long id){
		try {
		      menuService.toDeleteFoodFromMenu(id);
		      return ResponseUtils.prepareAcceptedResponse("Deleted SuccessFully",null);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("Id not found");
		}
	  
	}
	
	@PutMapping("/updatecost")
	public ResponseEntity<HttpStatusResponse> toUpdateTheFood(@RequestParam("id") Long id,@RequestParam("price") BigDecimal price){
	 try {
		menuService.toUpdateTheFood(id,price);
		return ResponseUtils.prepareAcceptedResponse("Deleted SuccessFully",null);
	 }
	 catch(BusinessServiceException exception) {
		 return ResponseUtils.prepareNoRecordFoundResponse("Id not found");
	 }
	}
	
	@GetMapping("/viewmenu")
	public ResponseEntity<HttpStatusResponse> toViewTheMenu(@RequestParam(value = "page")int page,@RequestParam(value = "size") int size){
		try {
			List<Menu> menu = menuService.toViewTheMenu(page,size);
			List<MenuDto> menudto =  menu.stream()
										  .map(this::convertToDto)
										  .collect(Collectors.toList());
			return ResponseUtils.prepareSuccessResponse("Menu", menudto);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("Id not found");
		}
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
	
	@PostMapping("/soldFood")
	public ResponseEntity<HttpStatusResponse> toGetFoodSoldData(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
		try {
			List<FoodSoldDto> foodSold = menuService.toGetTheFoodSold(fromDate, toDate);
			return ResponseUtils.prepareSuccessResponse("The food sold is", foodSold);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("The requested food is not available");
		}
			
		
	}
	
		
	

}
