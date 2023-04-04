package com.example.restaurant.dto;

import com.example.restaurant.model.Customer;

public class OrderDto {
	private Long id;
	private Long totalAmount;
	private Long customer;
	
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getCustomer() {
		return customer;
	}
	public void setCustomer(Long customer) {
		this.customer = customer;
	}
	public OrderDto(Long id, Long totalAmount) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
	}
	
	public OrderDto() {}
	
}
