package com.example.restaurant.service;

import java.util.List;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;
import com.example.restaurant.exception.InternalServerException;
import com.example.restaurant.exception.UserNotFoundException;
import com.example.restaurant.model.Customer;

public interface CustomerService {
	public void insertCustomer(CustomerDto customer) throws ContraintViolationException;
	public Customer authenticateCustomer(LoginDto loginDto) throws UserNotFoundException;
	public List<MenuDto> getMenu() throws InternalServerException;
	public void selectTheFoodItem(Long id,Long quantity) throws BusinessServiceException;
	public void toDeleteOrder(Long id)throws BusinessServiceException;
}
