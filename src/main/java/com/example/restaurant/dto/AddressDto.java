package com.example.restaurant.dto;

import java.util.List;

import com.example.restaurant.model.District;


public class AddressDto {
	private Long id;
	private String Area;
	private Long pincode;
	private Boolean isActive;
	//private List<DistrictDto> district;
	//private Long state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
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
	/*public List<DistrictDto> getDistrictValue() {
		return district;
	}
	public void setDistrictValue(List<DistrictDto> district) {
		this.district = district;
	}
	
		
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	public void addDistrictDTO(DistrictDto districtDTO) {
        this.district.add(districtDTO);
    }*/
	
	public AddressDto(Long id, String area, Long pincode, Boolean isActive) {
		super();
		this.id = id;
		Area = area;
		this.pincode = pincode;
		this.isActive = isActive;
		//this.district = district1;
	}
	
	
	
	
	
	
	
	
}
