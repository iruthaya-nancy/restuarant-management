package com.example.restaurant.dto;

public class DistrictDto {
	private Long id;
	private String name;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public DistrictDto() {
	}

	public DistrictDto(Long id2, String name2, Boolean isActive2) {
		id = id2;
		name = name2;
		isActive = isActive2;
		
	};

}
