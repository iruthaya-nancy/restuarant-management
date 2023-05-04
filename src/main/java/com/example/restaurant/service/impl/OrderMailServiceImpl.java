package com.example.restaurant.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.dto.AreaDto;
import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.dto.OrderEmail;
import com.example.restaurant.model.Area;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.District;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.SelectedFood;
import com.example.restaurant.model.State;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.SelectedFoodRepository;
import com.example.restaurant.service.OrderMailService;

@Service
public class OrderMailServiceImpl implements OrderMailService {

	@Autowired
	private CustomerRepository customerRepo;
	
		
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private SelectedFoodRepository selectedFoodRepo;
	
	@Autowired
	private  ModelMapper modelmapper;
	
	@Override
	public OrderEmail toSendMail(Long id,Long orderId) {
		OrderEmail orderEmail = new OrderEmail();
		Optional<Customer> customer = customerRepo.findById(id);
		if(Objects.nonNull(customer)) {
			CustomerDto cdto = convertCustomerToDto(customer.get());
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
			System.out.println(cdto.getId());
			Order order = orderRepo.findById(orderId).get();
			OrderDto odto = toConvertOrderToDto(order);
			orderEmail.setOrderdate(odto.getOrderDate());
			orderEmail.setTotal(odto.getTotalAmount());
			orderEmail.setOrderId(odto.getId());
			SelectedFood selectedFood = selectedFoodRepo.findById(odto.getId()).get();
			orderEmail.setQuantity(toGetQuantity(selectedFood));
		}
		return orderEmail;
	}
	

	private  CustomerDto convertCustomerToDto(Customer customer) {
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
	private  AreaDto convertAreaToDto(Area area) {
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
	
	private  OrderDto toConvertOrderToDto(Order order) {
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
	
	
//	private  List<MenuDto> toConvertMenutoDto(SelectedFood selectedfoods) {
//		MenuDto menuDto = modelmapper.map(menu,MenuDto.class);
//		List
//	}
	

}
