package com.example.restaurant.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.restaurant.model.SelectedFood;

public class MenuDto {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean isActive;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return price;
	}
	public void setAmount(BigDecimal price) {
		this.price = price;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public MenuDto() {}
	
	public MenuDto(Long id, String name, String description, BigDecimal price, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isActive = isActive;
	}
	
	

}
