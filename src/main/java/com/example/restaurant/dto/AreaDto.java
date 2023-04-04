package com.example.restaurant.dto;

import java.util.List;

import com.example.restaurant.model.District;


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



//	public String getDoorNo() {
//		return doorNo;
//	}
//
//
//
//	public void setDoorNo(String doorNo) {
//		this.doorNo = doorNo;
//	}
//
//
//
//	public String getStreetName() {
//		return streetName;
//	}
//
//
//
//	public void setStreetName(String streetName) {
//		this.streetName = streetName;
//	}



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

	public AreaDto() {}

	public AreaDto(Long id, String area, Long pincode, Boolean isActive) {
		super();
		this.id = id;
		this.name = area;
		//this.doorNo = doorNo;
		//this.streetName = streetName;
		this.pincode = pincode;
		this.isActive = isActive;
		
		//this.district = district1;
	}
	
	
	
	
	
	
	
	
}
