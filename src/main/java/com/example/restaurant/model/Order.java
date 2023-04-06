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
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TOTAL",columnDefinition="Decimal(10,2) default '0.00'")// passed from the food menu
	private Double totalAmount;
	
	// customer id 
	@OneToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
	
	// mode of payment
	 @OneToOne
	 @JoinColumn(name = "PAYMENT_MODE_ID")
	 private PaymentMode mode;
	 
	 //one order with many food
	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<SelectedFood> items;
	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_AT")
	private Date modifiedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
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
    
	
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Order() {}
	public Order(Long id) {
		super();
		this.id = id;
	}
	
	

}
