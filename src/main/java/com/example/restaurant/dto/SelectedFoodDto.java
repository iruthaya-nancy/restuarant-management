package com.example.restaurant.dto;

public class SelectedFoodDto {
	private Long id;
	private Long quantity;
	private MenuDto menu;
	private OrderDto order;
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
	/*public MenuDto getMenu() {
		return menu;
	}
	public void setMenu(MenuDto menu) {
		this.menu = menu;
	}*/
	
	
	
	public OrderDto getOrder() {
		return order;
	}
	public MenuDto getMenu() {
		return menu;
	}
	public void setMenu(MenuDto menu) {
		this.menu = menu;
	}
	public void setOrder(OrderDto order) {
		this.order = order;
	}
	
	public SelectedFoodDto() {}
	public SelectedFoodDto(Long id, Long quantity, MenuDto menu, OrderDto order) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.menu = menu;
		this.order = order;
	}
	
	
	
	

}
