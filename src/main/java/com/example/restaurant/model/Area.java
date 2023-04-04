package com.example.restaurant.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name ="AREA")
public class Area {
	@Id
	@Column(name = "ID", unique = true,nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DOOR_NO",unique = true,nullable = false)
	private String doorNo;
	
	@Column(name  = "STREET_NAME",length = 100,unique = true,nullable = false)
	private String streetName;
	
	@Column(name = "NAME",unique = true,nullable = false)
	private String name;
	
	@Column(name = "PINCODE", unique = true,nullable = false)
	private Long pincode;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getName() {
		return name;
	}

	public void setName(String area) {
		this.name = area;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer1) {
		this.customer = customer1;
	}
	
	
	public Area(Long id, String doorNo,String streetName,String name, Long pincode, Boolean isActive) {
		super();
		this.id = id;
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.name = name;
		this.pincode = pincode;
		this.isActive = isActive;
	}

	public Area() {
		// TODO Auto-generated constructor stub
	}

}
