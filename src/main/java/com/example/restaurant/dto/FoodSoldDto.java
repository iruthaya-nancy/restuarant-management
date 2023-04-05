package com.example.restaurant.dto;

public class FoodSoldDto {
	private String name;
	private Long Count;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return Count;
	}
	public void setCount(Long count) {
		Count = count;
	}
	public FoodSoldDto(String name, Long count) {
		super();
		this.name = name;
		Count = count;
	}
	public FoodSoldDto() {
		// TODO Auto-generated constructor stub
	}
	
	

}
