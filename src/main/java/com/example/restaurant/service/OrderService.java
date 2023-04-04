package com.example.restaurant.service;

import java.util.List;

import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.dto.SelectedFoodDto;

public interface OrderService {
     public void createOrder(List<SelectedFoodDto> selectedFoods,Long customerId) throws Exception;
}
