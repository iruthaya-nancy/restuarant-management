package com.example.restaurant.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restaurant.dto.CustomerDto;

import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.DuplicateException;
import com.example.restaurant.exception.InternalServerException;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Area;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.District;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.SelectedFood;
import com.example.restaurant.model.State;
import com.example.restaurant.model.Order;

import com.example.restaurant.repository.CustomerRepository;

import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderRepository;

import com.example.restaurant.repository.SelectedFoodRepository;

import com.example.restaurant.service.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private MenuRepository menuRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SelectedFoodRepository selectedFoodRepo;
	


	@Transactional // signup
	public Long insertCustomer(CustomerDto customerdto) throws DuplicateException
	{
		Customer customer;
		if(customerdto!=null)
		{
			try {
		    customer = new Customer();
			customer.setFirstName(customerdto.getFirstName());
			customer.setLastName(customerdto.getLastName());
			String pass = customerdto.getPassword();
			String hashedPassword = passwordEncoder.encode(pass);
			customer.setPassword(hashedPassword);
			customer.setEmail(customerdto.getEmail());
			customer.setPhoneNumber(customerdto.getPhoneNumber());
			customer.setDoorNo(customerdto.getDoorNo());
			customer.setStreet(customerdto.getStreet());
			customerRepo.save(customer);
			Area address = this.modelMapper.map(customerdto.getArea(), Area.class);
			customer.setArea(address);
			District district = this.modelMapper.map(customerdto.getDistrict(), District.class);
			customer.setDistrict(district);
			State state = this.modelMapper.map(customerdto.getState(), State.class);
			customer.setState(state);
			customerRepo.save(customer);
			}catch(DataIntegrityViolationException e) {
				throw new DuplicateException(e,"User Already exist");
			}
			
			
		} 
		else {
			throw new DuplicateException("User Already exist");
		}
		return customer.getId();
	}

	// login
	public Long authenticateCustomer(LoginDto loginDto) throws NotFoundException {
		Customer customer = customerRepo.findByEmail(loginDto.getEmail());
		if (customer != null && passwordEncoder.matches(loginDto.getPassword(), customer.getPassword())) {
			return customer.getId();
		} else {
			throw new NotFoundException("Please Check your username or Password");
		}
	}

	// menu items
	public List<MenuDto> getMenu() throws InternalServerException{
		List<Menu> menuItems = menuRepo.findByIsActiveIsTrue();
		List<MenuDto> menu = new ArrayList<>();
		for (Menu item : menuItems) {
			MenuDto dto = new MenuDto();
			dto.setId(item.getId());
			dto.setName(item.getName());
			dto.setDescription(item.getDescription());
			dto.setIsActive(item.getIsActive());
			dto.setAmount(item.getAmount());
			menu.add(dto);
		}

		return menu;
	}
	
	// to get the menu of the particular id we need order id from which we can get the food id from which 
	public List<MenuDto>  toGetOrderedFood(Long id) throws BusinessServiceException {// orderId
		   List<Menu> menus = selectedFoodRepo.findByOrderId(id).stream().map(SelectedFood::getMenu).filter(Objects::nonNull)
					.collect(Collectors.toList());
		   if(menus.isEmpty()) {
				throw new BusinessServiceException("Selected menu id not available");
			}
		   List<MenuDto> menu = new ArrayList<>();
		   for(Menu m:menus) {
			   MenuDto dto = new MenuDto();
			   dto.setId(m.getId());
			   dto.setName(m.getName());
			   dto.setDescription(m.getDescription());
			   dto.setAmount(m.getAmount());
			   dto.setIsActive(m.getIsActive());
			   menu.add(dto);
		   }
		   return menu;
		
	}
	
	public List<MenuDto> toGetMenuById(List<Long> ids) throws BusinessServiceException{
		List<MenuDto> m = new ArrayList<>();
		
		for(Long id:ids) {
			Menu menu = menuRepo.findById(id).get();
			MenuDto menuDto = new MenuDto();
			menuDto.setId(menu.getId());
			menuDto.setName(menu.getName());
			menuDto.setDescription(menu.getDescription());
			menuDto.setAmount(menu.getAmount());
			m.add(menuDto);
		}
		return m;
	}

	// add items to selected food to view another api
	public void selectTheFoodItem(Long id, Long quantity) throws BusinessServiceException{
		Optional<Menu> menuItem = menuRepo.findById(id);
		if (menuItem.isPresent()) {
			Order o = new Order();
			Menu menu = menuItem.get();
			orderRepo.save(o);
			SelectedFood s = new SelectedFood();
			s.setMenu(menu);
			s.setQuantity(quantity);
			s.setOrder(o);
			selectedFoodRepo.save(s);
		}

	}

	public void toDeleteOrder(Long id) throws BusinessServiceException{
		if(orderRepo.existsById(id)){
				orderRepo.deleteById(id);
		}
		else {
			throw new BusinessServiceException("Id does not exist");
		}
	}
  
	
	
	
	

}