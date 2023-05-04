package com.example.restaurant.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restaurant.dto.CustomerDto;

import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;
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
import com.example.restaurant.service.EmailService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private Object passwordResetTokenExpiration;
	
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
	public Long insertCustomer(CustomerDto customerdto) throws ContraintViolationException
	{
		Customer customer;
		if(customerdto!=null)
		{
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
			// for address
//		List<AreaDto> addressDtos = customerdto.getAddress();
//		if (addressDtos != null && !addressDtos.isEmpty()) {
//			List<Area> addresses = new ArrayList<>();
//			for (AreaDto addressDto : addressDtos) {
//				Area address = this.modelMapper.map(addressDto, Area.class);
//				address.setCustomer(customer);
//				addresses.add(address);
//				addressRepo.save(address);
//			}
//			customer.setAddress(addresses);
//			// customerRepo.save(customer);
//		}
//		// for district
//		List<DistrictDto> districtDtos = customerdto.getDistrict();
//		if (districtDtos != null && !districtDtos.isEmpty()) {
//			List<District> districts = new ArrayList<>();
//			for (DistrictDto districtdto : districtDtos) {
//				District district = this.modelMapper.map(districtdto, District.class);
//				district.setCustomer(customer);
//				districts.add(district);
//				districtRepo.save(district);
//			}
//			customer.setDistrict(districts);
//			// customerRepo.save(customer);
//		}
//		// for state
//		List<StateDto> stateDtos = customerdto.getState();
//		if (stateDtos != null && !stateDtos.isEmpty()) {
//			List<State> states = new ArrayList<>();
//			for (StateDto statedto : stateDtos) {
//				State state = this.modelMapper.map(statedto, State.class);
//				state.setCustomer(customer);
//				states.add(state);
//				stateRepo.save(state);
//			}
//			customer.setState(states);
			//return customer.getId();
		} 
		else {
			throw new ContraintViolationException("Please check the data");
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
		// List<SelectedFoodDto> selectFood = new ArrayList<>();
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
  
	// password resetting
	public void updateResetPasswordToken(String token, String email) throws NotFoundException {
		Customer customer = customerRepo.findByEmail(email);
		if (customer != null) {
			customer.setResetPasswordToken(token);
			customerRepo.save(customer);
		} else {
			throw new NotFoundException("Could not find any customer with the email " + email);
		}
	}

	public Customer getByResetPasswordToken(String token) {
		return customerRepo.findByResetPasswordToken(token);
	}

	public void updatePassword(Customer customer, String newPassword) {
		String encodedPassword = passwordEncoder.encode(newPassword);
		customer.setPassword(encodedPassword);

		customer.setResetPasswordToken(null);
		customerRepo.save(customer);
	}
	
	public void createPasswordResetTokenForUser(String token,String email) {
//		String token = null;
        Customer user = customerRepo.findByEmail(email);
        if (user != null) {
////            String token = user.createPasswordResetTokenForUser();
////            //customerRepo.save(user);
////            emailService.sendPasswordResetToken(token);
//        	token = UUID.randomUUID().toString();
            user.setResetPasswordToken(token);
            passwordResetTokenExpiration = LocalDateTime.now().plusHours(24);
            customerRepo.save(user);
        }
		//return token;
    }

	
	
	

}