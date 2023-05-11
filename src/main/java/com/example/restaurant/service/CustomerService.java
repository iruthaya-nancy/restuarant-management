package com.example.restaurant.service;

import java.util.List;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ConstraintViolationException;
import com.example.restaurant.exception.InternalServerException;
import com.example.restaurant.exception.NotFoundException;


import jakarta.transaction.TransactionRolledbackException;

public interface CustomerService {
	public Long insertCustomer(CustomerDto customer) throws TransactionRolledbackException,ConstraintViolationException;
	public Long authenticateCustomer(LoginDto loginDto) throws NotFoundException;
	public List<MenuDto> getMenu() throws InternalServerException;
	public void selectTheFoodItem(Long id,Long quantity) throws BusinessServiceException;
	public void toDeleteOrder(Long id)throws BusinessServiceException;
	public List<MenuDto> toGetOrderedFood(Long id)throws BusinessServiceException;
	public List<MenuDto> toGetMenuById(List<Long> id) throws BusinessServiceException;
}
