package com.example.restaurant.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SELECTED_FOOD")
public class SelectedFood {
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "QUANTITY", columnDefinition = "int default 1")
	private Long quantity;

	//@Column(name = "QUANTITY_AMOUNT", precision = 10, scale = 2)
	//private BigDecimal quantityAmount;
	

	// multiple food selected
	@ManyToOne
	@JoinColumn(name = "MENU_ID")
	//private Menu menu;
	private Menu menu;

	// multiple food will be one order
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private Order order;

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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/*public BigDecimal getQuantityAmount() {
		return quantityAmount;
	}

	public void setQuantityAmount(BigDecimal quantityAmount) {
		this.quantityAmount = quantityAmount;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}*/
	
	

	public Order getOrder() {
		return order;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public SelectedFood() {}

	public SelectedFood(Long id, Long quantity, Menu menu, Order order) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.menu = menu;
		this.order = order;
	}
	
	
	
	
}
