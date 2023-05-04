package com.example.restaurant.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.itextpdf.text.DocumentException;

import jakarta.mail.MessagingException;

public interface OrderService {
     public Long createOrder(List<SelectedFoodDto> selectedFoods,Long customerId,Long paymentId) throws BusinessServiceException, UnsupportedEncodingException, MessagingException;
     public void sendOrderEmail(Long id,Long orderId,List<SelectedFoodDto> selectedFoods) throws MessagingException, UnsupportedEncodingException, DocumentException;
}
