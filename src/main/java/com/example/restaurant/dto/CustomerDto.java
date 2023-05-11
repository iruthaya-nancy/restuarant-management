package com.example.restaurant.dto;

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

	// return entity
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerDto [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", doorNo=");
		builder.append(doorNo);
		builder.append(", street=");
		builder.append(street);
		builder.append(", areadto=");
		builder.append(areadto);
		builder.append(", districtdto=");
		builder.append(districtdto);
		builder.append(", statedto=");
		builder.append(statedto);
		builder.append(", area=");
		builder.append(area);
		builder.append(", district=");
		builder.append(district);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}

}
