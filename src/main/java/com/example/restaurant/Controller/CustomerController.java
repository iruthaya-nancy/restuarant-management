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
import com.example.restaurant.dto.MenuDto;

import com.example.restaurant.model.Customer;
import com.example.restaurant.service.serviceImpl.CustomerServiceImpl;

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
		public ResponseEntity<String> toLogin(@RequestParam("email") String email,
                @RequestParam("password") String password,
                HttpSession session){
			Customer customer = customerService.authenticateCustomer(email, password);
			if (customer != null) {
	            session.setAttribute("customer", customer);
	           //HttpHeaders headers = new HttpHeaders();
	            //headers.setLocation(URI.create("/menu"));
	            return ResponseEntity.ok("Login successful");
	            //return new ResponseEntity<>(headers, HttpStatus.FOUND);
	        } 
			else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	        }
		}
		
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
		public ResponseEntity<String> toSelectFood(@RequestParam("id")Long id){
			MenuDto menudto = new MenuDto();
			customerService.selectTheFoodItem(id);
			return ResponseEntity.ok("food selected");
			
		}
		
		@DeleteMapping("/deleteorder")
		public ResponseEntity<String> toDeleteOrder(@RequestParam("id") Long id){
			customerService.toDeleteOrder(id);
			return ResponseEntity.ok("Order Deleted");
			
		}
}
