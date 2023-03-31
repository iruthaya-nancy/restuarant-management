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
	
	@Column(name = "AREA",unique = true,nullable = false)
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

		public Customer getCustomer1() {
		return customer;
	}

	public void setCustomer1(Customer customer1) {
		this.customer = customer1;
	}
	
	
	public Area(Long id, String area, Long pincode, Boolean isActive) {
		super();
		this.id = id;
		this.name = area;
		this.pincode = pincode;
		this.isActive = isActive;
	}

	public Area() {
		// TODO Auto-generated constructor stub
	}

}
