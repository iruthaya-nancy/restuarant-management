package com.example.restaurant.dto;

import java.util.Date;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.restaurant.model.Area;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.District;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.SelectedFood;
import com.example.restaurant.model.State;

import com.example.restaurant.repository.CustomerRepository;

import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.SelectedFoodRepository;


public class OrderEmail {
	private String firstName;
	private String lastName;
	private String doorNo;
	private String streetName;
	private String area;
	private String district;
	private String state;
	private Long pincode;
	private Long orderId;
	private List<String> items;  
	private Double total;
	private Date orderdate;
	//private Long orderNo;
	private Long quantity;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public List<String> getItems() {
		return items;
	}
	public void setItems(List<String> items) {
		this.items = items;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
//	public Long getOrderNo() {
//		return orderNo;
//	}
//	public void setOrderNo(Long orderNo) {
//		this.orderNo = orderNo;
//	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	@Autowired
	private static CustomerRepository customerRepo;
	
		
	@Autowired
	private static OrderRepository orderRepo;
	
	@Autowired
	private static SelectedFoodRepository selectedFoodRepo;
	
	@Autowired
	private static ModelMapper modelmapper;
	
	
	public static OrderEmail toSendMail(Long id) {
		OrderEmail orderEmail = new OrderEmail();
		Customer customer = customerRepo.findById(id).get();
		CustomerDto cdto = convertCustomerToDto(customer);
		Area area = cdto.getAreadto();
		AreaDto adto = convertAreaToDto(area);
		District districtEntity = cdto.getDistrictDto();
		State stateEntity = cdto.getStatedto();
		orderEmail.setFirstName(cdto.getFirstName());
		orderEmail.setLastName(cdto.getLastName());
		orderEmail.setDoorNo(cdto.getDoorNo());
		orderEmail.setStreetName(cdto.getStreet());
		orderEmail.setArea(adto.getName());
		orderEmail.setPincode(adto.getPincode());
		orderEmail.setDistrict(toGetDistrict(districtEntity));
		orderEmail.setState(toGetState(stateEntity));
		Order order = orderRepo.findById(cdto.getId()).get();
		OrderDto odto = toConvertOrderToDto(order);
		orderEmail.setOrderdate(odto.getOrderDate());
		orderEmail.setTotal(odto.getTotalAmount());
		orderEmail.setOrderId(odto.getId());
		SelectedFood selectedFood = selectedFoodRepo.findById(odto.getId()).get();
		orderEmail.setQuantity(toGetQuantity(selectedFood));
		return orderEmail;
		
	}

	
	private static CustomerDto convertCustomerToDto(Customer customer) {
		CustomerDto customerdto = modelmapper.map(customer,CustomerDto.class);
		customerdto.setFirstName(customer.getFirstName());
		customerdto.setLastName(customer.getLastName());
		customerdto.setDoorNo(customer.getDoorNo());
		customerdto.setStreet(customer.getStreet());
		customerdto.setAreadto(customer.getArea());
		customerdto.setDistrictdto(customer.getDistrict());
		customerdto.setStatedto(customer.getState());
		return customerdto;
	}
	//to get area
	private static AreaDto convertAreaToDto(Area area) {
		AreaDto areadto = modelmapper.map(area, AreaDto.class);
		//areadto.setId(area.getId());
		areadto.setName(area.getName());
		areadto.setPincode(area.getId());
		//areadto.setIsActive(area.getIsActive());
		return areadto;
		
	}
	
	private static String toGetDistrict(District district) {
		  return  district.getName();
	}
	
	private static String toGetState(State state) {
		return  state.getName();
	
	}
	
	private static OrderDto toConvertOrderToDto(Order order) {
		OrderDto orderDto = modelmapper.map(order,OrderDto.class);
		orderDto.setId(order.getId());
		orderDto.setTotalAmount(order.getTotalAmount());
		orderDto.setOrderDate(order.getCreatedAt());
	    return orderDto;	
	}
	
	//to get the quantity name
	private static Long toGetQuantity(SelectedFood selectedFoods) {
		return  selectedFoods.getQuantity();	
	}
	
	

}
