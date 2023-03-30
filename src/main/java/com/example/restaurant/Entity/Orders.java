package com.example.restaurant.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// final table
// mode one to one order
@Entity
@Table(name = "ORDER")//
public class Orders {
	@Id
	@Column(name = "ID",unique = true,nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TOTAL",nullable = false)// passed from the food menu
	private Long totalAmount;
	
	// customer id 
	@OneToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
	
	// mode of payment
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "PAYMENT_ID")
	 private PaymentMode mode;
	 
	 //one order with many food
	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<SelectedFood> items = new ArrayList<>();
	
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFY_DATE")
	private Date modifyDate;
	

}
