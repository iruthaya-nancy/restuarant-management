package com.example.restaurant.Controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.exception.UserNotFoundException;
import com.example.restaurant.model.Customer;
import com.example.restaurant.service.impl.CustomerServiceImpl;

import jakarta.servlet.http.HttpSession;


@RestController
public class CustomerController {
		@Autowired
		public CustomerServiceImpl customerService;
		
		
		@PostMapping("/signup")
		public ResponseEntity<Void> toCreateUser(@RequestBody CustomerDto customerDtoImpl){
			customerService.insertCustomer(customerDtoImpl);
			 return ResponseEntity.status(HttpStatus.CREATED).build();
			
		}
		
		@PostMapping("/login")
		public ResponseEntity<String> toLogin(@RequestBody LoginDto loginDto,
                HttpSession session) throws UserNotFoundException{
			
			try {
				 Customer customer = customerService.authenticateCustomer(loginDto);
				session.setAttribute("customer", customer);
				 return ResponseEntity.ok("Login successful");
			} 
			catch (UserNotFoundException exception) {
				// TODO Auto-generated catch block
				throw exception;
			}
		}
	            
	           //HttpHeaders headers = new HttpHeaders();
	            //headers.setLocation(URI.create("/menu"));
	           
	          //return new ResponseEntity<>(headers, HttpStatus.FOUND);
			
		
		@GetMapping("/menu")
		public ResponseEntity<List<MenuDto>> toGetMenu(HttpSession session){
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer != null) {
	            List<MenuDto> menu = customerService.getMenu();
	            return ResponseEntity.ok(menu);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }
		
		@PostMapping("/foodorder")
		public ResponseEntity<String> toSelectFood(@RequestParam("id")Long id,@RequestParam("quantity")Long quantity){
			//MenuDto menudto = new MenuDto();
			customerService.selectTheFoodItem(id,quantity);
			return ResponseEntity.ok("food selected");
			
		}
		
		@DeleteMapping("/deleteorder")
		public ResponseEntity<String> toDeleteOrder(@RequestParam("id") Long id){
			customerService.toDeleteOrder(id);
			return ResponseEntity.ok("Order Deleted");
			
		}
		
		//Reset password
		
}
