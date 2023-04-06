package com.example.restaurant.dto;

import java.util.Date;

public class OrderDto {
	private Long id;
	private Double totalAmount;
	private Long customerId;
	private Date orderDate;
	
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customer) {
		this.customerId = customer;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public OrderDto(Long id, Double totalAmount) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
	}
	
	public OrderDto() {}
	
}
