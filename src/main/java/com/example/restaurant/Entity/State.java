package com.example.restaurant.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "STATE")
public class State {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME",length = 20)
	private String name;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	 @OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "DISTRICT_ID")
	 private District district;
	 
	 @OneToOne
	 @JoinColumn(name = "person_id")
	 private Customer customer;
}
