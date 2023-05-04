package com.example.restaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;
import com.example.restaurant.exception.InternalServerException;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Customer;
import com.example.restaurant.service.impl.CustomerServiceImpl;
import com.example.restaurant.util.HttpStatusResponse;
import com.example.restaurant.util.ResponseUtils;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	private CustomerServiceImpl customerService;

	@PostMapping("/signup")
	public ResponseEntity<HttpStatusResponse> toCreateUser(@RequestBody CustomerDto customerDtoImpl) {
		try {
			Long customerId = customerService.insertCustomer(customerDtoImpl);
			return ResponseUtils.prepareSuccessResponse(null, customerId);
		} catch (ContraintViolationException exception) {
			return ResponseUtils.prepareUnProcessableEntityResponse(
					"Failed to create new customer .Please enter the suitable data");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<HttpStatusResponse> toLogin(@RequestBody LoginDto loginDto, HttpSession session) {

		try {
			Long customer = customerService.authenticateCustomer(loginDto);
			session.setAttribute("customer", customer);
			return ResponseUtils.prepareSuccessResponse(null, customer);
		} catch (NotFoundException exception) {
			// TODO Auto-generated catch block
			return ResponseUtils.prepareUnAuthorizedResponse("Invalid Username or Password");
		}
	}

	// HttpHeaders headers = new HttpHeaders();
	// headers.setLocation(URI.create("/menu"));

	// return new ResponseEntity<>(headers, HttpStatus.FOUND);

	@GetMapping("/menu")
	public ResponseEntity<HttpStatusResponse> toGetMenu(HttpSession session) {
		try {
			// Customer customer = (Customer) session.getAttribute("customer");// stores the
			// customer data in session
			// if (customer != null) {
			List<MenuDto> menu = customerService.getMenu();
			return ResponseUtils.prepareSuccessResponse("Today's Menu", menu);
		} catch (InternalServerException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("Menu Not Available");
		}
	}
	
	@GetMapping("/foodselected")
	public ResponseEntity<HttpStatusResponse> toGetOrderedFood(@RequestParam("id") Long id) {
		try {
			// Customer customer = (Customer) session.getAttribute("customer");// stores the
			// customer data in session
			// if (customer != null) {
			List<MenuDto> menu = customerService.toGetOrderedFood(id);
			return ResponseUtils.prepareSuccessResponse("Selected food", menu);
		} catch ( BusinessServiceException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("Menu Not Available");
		}
	}
	
	@PostMapping("/menuid")
	public ResponseEntity<HttpStatusResponse> toGetFood(@RequestBody List<Long> ids) {
		try {
			// Customer customer = (Customer) session.getAttribute("customer");// stores the
			// customer data in session
			// if (customer != null) {
			List<MenuDto> menu = customerService.toGetMenuById(ids);
			return ResponseUtils.prepareSuccessResponse("Selected food", menu);
		} catch ( BusinessServiceException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("Food Not Available");
		}
	}


//	@PostMapping("/foodorder")
//	public ResponseEntity<HttpStatusResponse> toSelectFood(@RequestParam("id") Long id,
//			@RequestParam("quantity") Long quantity) {
//		try {
//			customerService.selectTheFoodItem(id, quantity);
//			return ResponseUtils.prepareSuccessResponse("Login successful", null);
//		} catch (BusinessServiceException exception) {
//			return ResponseUtils.prepareNoRecordFoundResponse("The requested food is not available");
//		}
//	}

	@DeleteMapping("/order")
	public ResponseEntity<HttpStatusResponse> toDeleteOrder(@RequestParam("id") Long id) {
		try {
		customerService.toDeleteOrder(id);
		return ResponseUtils.prepareSuccessResponse("OrderDeleted Successfully",null);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareUnAuthorizedResponse("Invalid order id");
		}
	}

	// Reset password

}

