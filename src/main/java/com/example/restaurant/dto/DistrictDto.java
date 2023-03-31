package com.example.restaurant.dto;



public class DistrictDto {
	private Long id;
	private String name;
	private Boolean isActive;
	//private AddressDto address;
	//private StateDto state;

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
	/*public StateDto getState() {
		return state;
	}
	public void setState(StateDto state) {
		this.state = state;
	}*/
	
	
	
	/*public DistrictDto(Long id, String name, Boolean isActive, AddressDto address, StateDto state) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
		this.address = address;
		this.state = state;
	}
	public AddressDto getAddress() {
		return address;
	}
	public void setAddress(AddressDto address) {
		this.address = address;
	}*/
	public DistrictDto() {}
	public DistrictDto(Long id2, String name2, Boolean isActive2) {//, AddressDto address2, StateDto state2) {
		// TODO Auto-generated constructor stub
		id = id2;
		name = name2;
		isActive = isActive2;
		//address = address2;
		//state = state2;
	};
	
	
}
