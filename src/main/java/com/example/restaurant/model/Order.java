package com.example.restaurant.model;

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


@Entity
@Table(name = "ORDERS")
public class Order {
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
	 @JoinColumn(name = "PAYMENT_MODE_ID")
	 private PaymentMode mode;
	 
	 //one order with many food
	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<SelectedFood> items;
	
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_AT")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_AT")
	private Date modifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer sgetCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	public List<SelectedFood> getItems() {
		return items;
	}

	public void setItems(List<SelectedFood> items) {
		this.items = items;
	}

	public Order() {}
	public Order(Long id) {
		super();
		this.id = id;
	}
	
	

}
