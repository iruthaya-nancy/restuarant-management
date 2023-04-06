package com.example.restaurant.model;

import java.math.BigDecimal;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
// one to many relation with the selected food items not with the customer
@Entity
@Table(name = "MENU")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE",precision = 10,scale = 2)
	private BigDecimal price;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
//	
//	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SelectedFood> food;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return price;
	}

	public void setAmount(BigDecimal price) {
		this.price = price;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

//	public List<SelectedFood> getFood() {
//		return food;
//	}
//
//	public void setFood(List<SelectedFood> food) {
//		this.food = food;
//	}

	public Menu(Long id) {
		super();
		this.id = id;
	}
	
	public Menu() {}
	
	
	
	
}
