package com.example.restaurant.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "DISTRICT")
public class District {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME",length = 20)
	private String name;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
	
		
	@OneToOne(mappedBy = "district", cascade = CascadeType.ALL)
    private State state;
	
	@OneToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
}
