package com.example.restaurant.dto;

public class AreaDto {
	private Long id;
	private String name;
	private Long pincode;
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

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public AreaDto() {
	}

	public AreaDto(Long id, String area, Long pincode, Boolean isActive) {
		super();
		this.id = id;
		this.name = area;
		this.pincode = pincode;
		this.isActive = isActive;

	}

}
