package com.example.restaurant.service.customerService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.Entity.Customer;
import com.example.restaurant.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {
	@Autowired
	public CustomerRepository customerRepo;
	
	@Transactional
	public String insertIntoCustomer(Customer customer) {
		customerRepo.save(customer);
		return "Inserted Successfully";
		
	}
	
	@Transactional
	public String deleteCustomer(Long id) {
		customerRepo.deleteById(id);
		return "deleted successfully";
	}
	
	

}

