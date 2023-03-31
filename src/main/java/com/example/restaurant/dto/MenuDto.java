package com.example.restaurant.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.restaurant.model.SelectedFood;

public class MenuDto {
	private Long id;
	private String name;
	private String description;
	private BigDecimal amount;
	private Boolean isActive;
	private List<SelectedFood> food;
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
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public List<SelectedFood> getFood() {
		return food;
	}
	public void setFood(List<SelectedFood> food) {
		this.food = food;
	}
	public MenuDto() {}
	
	public MenuDto(Long id, String name, String description, BigDecimal amount, Boolean isActive,
			List<SelectedFood> food) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.isActive = isActive;
		this.food = food;
	}
	
	

}
