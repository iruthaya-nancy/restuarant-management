package com.example.restaurant.service;

import java.util.List;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.exception.UserNotFoundException;
import com.example.restaurant.model.Customer;

public interface CustomerService {
	public void insertCustomer(CustomerDto customer);
	public Customer authenticateCustomer(LoginDto loginDto) throws UserNotFoundException;
	public List<MenuDto> getMenu();
	public void selectTheFoodItem(Long id,Long quantity);
	public void toDeleteOrder(Long id);
}
