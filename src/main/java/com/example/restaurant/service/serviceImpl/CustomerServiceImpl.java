package com.example.restaurant.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restaurant.dto.AreaDto;
import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.dto.DistrictDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.dto.StateDto;
import com.example.restaurant.model.Area;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.District;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.SelectedFood;
import com.example.restaurant.model.State;
import com.example.restaurant.model.Order;
import com.example.restaurant.repository.AddressRepository;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.repository.DistrictRepository;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.SelectedFoodRepository;
import com.example.restaurant.repository.StateRepository;
import com.example.restaurant.service.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	public CustomerRepository customerRepo;

	@Autowired
	public OrderRepository orderRepo;
	
	@Autowired
	public StateRepository stateRepo;

	@Autowired
	public DistrictRepository districtRepo;

	@Autowired
	public AddressRepository addressRepo;

	@Autowired
	public MenuRepository menuRepo;

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public SelectedFoodRepository selectedFoodRepo;

	@Transactional // signup
	public void insertCustomer(CustomerDto customerdto) {
		Customer customer = new Customer();
		customer.setId(customerdto.getId());
		customer.setFirstName(customerdto.getFirstName());
		customer.setLastName(customerdto.getLastName());
		String pass = customerdto.getPassword();
		// String hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt());
		String hashedPassword = passwordEncoder.encode(pass);
		customer.setPassword(hashedPassword);
		customer.setEmail(customerdto.getEmail());
		customer.setPhoneNumber(customerdto.getPhoneNumber());
		//customer.setDoorNo(customerdto.getDoorNo());
		//customer.setStreet(customerdto.getStreet());
		customerRepo.save(customer);
		// for address
		List<AreaDto> addressDtos = customerdto.getAddress();
		if (addressDtos != null && !addressDtos.isEmpty()) {
			List<Area> addresses = new ArrayList<>();
			for (AreaDto addressDto : addressDtos) {
				Area address = this.modelMapper.map(addressDto, Area.class);
				address.setCustomer(customer);
				addresses.add(address);
				addressRepo.save(address);
			}
			customer.setAddress(addresses);
			// customerRepo.save(customer);
		}
		// for district
		List<DistrictDto> districtDtos = customerdto.getDistrict();
		if (districtDtos != null && !districtDtos.isEmpty()) {
			List<District> districts = new ArrayList<>();
			for (DistrictDto districtdto : districtDtos) {
				District district = this.modelMapper.map(districtdto, District.class);
				district.setCustomer(customer);
				districts.add(district);
				districtRepo.save(district);
			}
			customer.setDistrict(districts);
			// customerRepo.save(customer);
		}
		// for state
		List<StateDto> stateDtos = customerdto.getState();
		if (stateDtos != null && !stateDtos.isEmpty()) {
			List<State> states = new ArrayList<>();
			for (StateDto statedto : stateDtos) {
				State state = this.modelMapper.map(statedto, State.class);
				state.setCustomer(customer);
				states.add(state);
				stateRepo.save(state);
			}
			customer.setState(states);
			
		}
		customerRepo.save(customer);

	}

	// login
	public Customer authenticateCustomer(String email, String password) {
		Customer customer = customerRepo.findByEmail(email);
		if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
			return customer;
		} else {
			return null;
		}
	}

	// menu items
	public List<MenuDto> getMenu() {
		List<Menu> menuItems = menuRepo.findAll();
		List<MenuDto> menu = new ArrayList<>();
		for (Menu item : menuItems) {
			MenuDto dto = new MenuDto();
			dto.setId(item.getId());
			dto.setName(item.getName());
			dto.setDescription(item.getDescription());
			dto.setAmount(item.getAmount());
			menu.add(dto);
		}

		return menu;
	}
	//add items to selected food to view another api
	public void selectTheFoodItem(Long id) {
		Optional<Menu> menuItem = menuRepo.findById(id);
		//List<SelectedFoodDto> selectFood = new ArrayList<>();
		if(menuItem!=null) {
		SelectedFoodDto sdto = new SelectedFoodDto();
		OrderDto order = new OrderDto();
		Order o = new Order();
		Menu menu = menuItem.get();
		MenuDto dto = new MenuDto();
		o.setId(id);
		orderRepo.save(o);
		dto.setId(menu.getId());
			if(id.equals(dto.getId())){
				SelectedFood s = new SelectedFood();
				s.setMenu(new Menu(dto.getId()));
				s.setQuantity(sdto.getQuantity());
				s.setOrder(new Order(order.getId()));
				selectedFoodRepo.save(s);
			}
			
		}
		
	}
	
	public void toDeleteOrder(Long id) {
		orderRepo.deleteById(id);
	}
	
	


}
