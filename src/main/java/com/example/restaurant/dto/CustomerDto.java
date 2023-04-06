package com.example.restaurant.dto;

import java.util.List;

import com.example.restaurant.model.Area;
import com.example.restaurant.model.District;
import com.example.restaurant.model.State;

public class CustomerDto {
	 private Long id;
	 private String firstName;
	 private String lastName;
	 private String password;
	 private String email;
	 private String phoneNumber;
	 private String doorNo;
	 private String street;
	 private AreaDto areadto;
	 private DistrictDto districtdto;
	 private StateDto statedto;
	 private Area area;
	 private District district;
	 private State state;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/*public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public List<AreaDto> getAddress() {
		return area;
	}
	public void setAddress(List<AreaDto> address) {
		this.area = address;
	}
	public List<DistrictDto> getDistrict() {
		return district;
	}
	public void setDistrict(List<DistrictDto> district) {
		this.district = district;
	}
	public List<StateDto> getState() {
		return state;
	}
	public void setState(List<StateDto> state) {
		this.state = state;
	}
	
	
	public void addAddressDTO(AreaDto addressDTO) {
        this.area.add(addressDTO);
    }
	
	public CustomerDto() {}
	public CustomerDto(Long id, String firstName, String lastName, String password, String email,
			String phoneNumber){/* {, String doorNo, String street) {/*, List<AddressDto> address, DistrictDto district,
			StateDto state) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		//this.doorNo = doorNo;
		//this.street = street;
		//this.address = address;
		//this.district = district;
		//this.state = state;
	}
	*/
	
	
	public AreaDto getArea() {
		return areadto;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setArea(AreaDto areadto) {
		this.areadto = areadto;
	}
	public DistrictDto getDistrict() {
		return districtdto;
	}
	public void setDistrict(DistrictDto districtdto) {
		this.districtdto = districtdto;
	}
	public StateDto getState() {
		return statedto;
	}
	public void setState(StateDto statedto) {
		this.statedto = statedto;
	}
	
	//return entity 
	public Area getAreadto() {
		return area;
	}
	public void setAreadto(Area area) {
		this.area = area;
		
	}
	public District getDistrictDto() {
		return district;
	}
	public void setDistrictdto(District district) {
			this.district = district;
		
	}
	public State getStatedto() {
		return state;
	}
	public void setStatedto(State state) {
		this.state = state;
		
	}
	
	
	
	 
	 
}
