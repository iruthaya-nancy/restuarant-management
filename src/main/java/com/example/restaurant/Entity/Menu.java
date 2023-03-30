package com.example.restaurant.Entity;

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
	@Column(name = "ID",unique = true,nullable = false)
	private Long id;

	@Column(name = "ITEM")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "AMOUNT",precision = 10,scale = 2)
	private BigDecimal amount;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectedFood> food = new ArrayList<>();

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFY_DATE")
	private Date modifyDate;

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
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<SelectedFood> getFood() {
		return food;
	}

	public void setFood(List<SelectedFood> food) {
		this.food = food;
	}
	
	
}
