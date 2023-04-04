package com.example.restaurant.dto;



public class OrderDto {
	private Long id;
	private Double totalAmount;
	private Long customerId;
	
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
	public OrderDto(Long id, Double totalAmount) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
	}
	
	public OrderDto() {}
	
}
