package com.example.restaurant.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Data
@Table(name ="ADDRESS")
public class Address {
	@Id
	@Column(name = "ID", unique = true,nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "AREA",unique = true,nullable = false)
	private String area;
	
	@Column(name = "PINCODE", unique = true,nullable = false)
	private Long pincode;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@OneToMany(mappedBy = "address",cascade = CascadeType.ALL)
	private List<District> district;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
	private Customer customer1;
}
