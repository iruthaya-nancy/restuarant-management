package com.example.restaurant.service.impl;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.el.parser.AstDotSuffix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.dto.OrderEmail;
import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.PaymentMode;
import com.example.restaurant.model.SelectedFood;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.SelectedFoodRepository;
import com.example.restaurant.service.OrderService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class OrderServiceImpl implements OrderService {
    
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private SelectedFoodRepository selectedFoodRepository;
	

    @Autowired
    private JavaMailSender mailSender;

	/* public Optional<Order> getOrderById(Long orderId) {
	        return order.findById(orderId);
	    }
	*/
	@Transactional
	public void createOrder(List<SelectedFoodDto> selectedFoods, Long customerId,Long paymentid) throws BusinessServiceException, UnsupportedEncodingException,MessagingException {
//		Customer customer = new Customer();
//		Order order = new Order();
//		order.setId(orderDto.getId());
//	    order.setCustomer(new Customer(customer.getId()));
//	    order.setTotalAmount(orderDto.getTotalAmount());
//	    orderRepo.save(order);
		if(!CollectionUtils.isEmpty(selectedFoods)) {
			List<Long> id = selectedFoods.stream().map(SelectedFoodDto::getMenuId)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
			List<Menu> menus = menuRepository.findByIdInAndIsActiveIsTrue(id);
			if(!CollectionUtils.isEmpty(menus)) {
				if(menus.size() != selectedFoods.size()) {
					throw new BusinessServiceException("Selected menu id not available");
				}
				Order order = new Order();
				order.setTotalAmount(calculateTotal(selectedFoods, menus));
				order.setCustomer(new Customer(customerId));
				order.setMode(new PaymentMode(paymentid));
				
				orderRepo.save(order);
				List<SelectedFood> sf = new ArrayList<>();
				selectedFoods.forEach(food ->{
				SelectedFood se = new SelectedFood();
				se.setMenu(new Menu(food.getMenuId()));
				se.setQuantity(food.getQuantity());
				se.setOrder(order);
				sf.add(se);
				});
				selectedFoodRepository.saveAll(sf);
				try {
				     sendOrderEmail(customerId);
			     }
				catch(MessagingException exception) {
					throw new BusinessServiceException("cannot mail");
				}
				}
		}
		    
	}
	
	private  void sendOrderEmail(Long id)
	        throws MessagingException, UnsupportedEncodingException {
		Customer customer = new Customer();
		String receiver = customer.getEmail();
		OrderEmail email = OrderEmail.toSendMail(id);
		
		MimeMessage message = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	    helper.setFrom("iruthaya05@gmail.com");
	    helper.setTo(customer.getEmail());
	    String subject = "Here's your Order";
	     
	    String content = "<p>Hello,</p>"
	        	+ "<p>Your Order is</p>"
	            +"<p>FirstName:"+email.getFirstName()+"</p>"
	            + "<br>"
	            +"<p>LastName:"+email.getLastName()+"</p>"
	            +"<p>\"DooorNumber:"+email.getDoorNo()+"</p>"
	            +"<p>StreetName:"+email.getStreetName()+"</p>"
	            +"<p>Area:"+email.getArea()+"</p>"
	            +"<p>District:"+email.getDistrict()+"</p>"
	    		+"<p>State:"+email.getState()+"</p>"
	    		+"<p>Pincode:"+email.getPincode()+"</p>"
	    		+"<p>Your ordernumber:"+email.getOrderId()+"</p>"
	    		+"<p>Order placed on:"+email.getOrderdate()+"</p>"
	    		+"<p>Quantity :"+email.getQuantity()+"</p>";
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	    
	}

	private Double calculateTotal(List<SelectedFoodDto> selectedFoods, List<Menu> menus) {
		// check the id from menu and selected food
	        	Double total = null;
	    		for(SelectedFoodDto sdto:selectedFoods) 
	    		{
	    			Optional<Menu> optionalMenu = menuRepository.findById(sdto.getId());
	    			if(!optionalMenu.isEmpty()) {
	    				Menu m = optionalMenu.get();
	    			    total = sdto.getQuantity().doubleValue()*m.getAmount().doubleValue();
	    	}
	   	}
				return total;
	 }
		
}
