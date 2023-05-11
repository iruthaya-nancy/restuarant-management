package com.example.restaurant.service.impl;


import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.restaurant.dto.OrderEmail;
import com.example.restaurant.dto.SelectedFoodDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.PaymentMode;
import com.example.restaurant.model.SelectedFood;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.SelectedFoodRepository;
import com.example.restaurant.service.OrderMailService;
import com.example.restaurant.service.OrderService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private SelectedFoodRepository selectedFoodRepository;
	

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private OrderMailService orderMail;

	@Transactional
	public Long createOrder(List<SelectedFoodDto> selectedFoods, Long customerId,Long paymentid) throws BusinessServiceException, UnsupportedEncodingException,MessagingException {

		Long orderId = null;
		try {
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
				orderId = order.getId();
				List<SelectedFood> sf = new ArrayList<>();
				selectedFoods.forEach(food ->{
				SelectedFood se = new SelectedFood();
				se.setMenu(new Menu(food.getMenuId()));
				se.setQuantity(food.getQuantity());
				se.setOrder(order);
				sf.add(se);
				});
				selectedFoodRepository.saveAll(sf);
				sendOrderEmail(customerId,orderId,selectedFoods);

			}
		}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return orderId;
		
		    
	}
	
	public void sendOrderEmail(Long id,Long orderId,List<SelectedFoodDto> selectedFoods) throws MessagingException, UnsupportedEncodingException, DocumentException {
		Customer customer = customerRepo.findById(id).get();
		String receiver = customer.getEmail();
		OrderEmail email = orderMail.toSendMail(id,orderId);
		List<Long> ids = selectedFoods.stream().map(SelectedFoodDto::getMenuId)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		List<Menu> menus = menuRepository.findByIdIn(ids);
		List<Long> quantity = selectedFoods.stream().map(SelectedFoodDto::getQuantity)
				.filter(Objects::nonNull).collect(Collectors.toList());
		Order o = orderRepo.findById(email.getOrderId()).get();
		
		MimeMessage msg = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(msg,true);
	    
	    Date orderDate = email.getOrderdate();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String formattedDate = dateFormat.format(orderDate);
		
	    helper.setFrom("iruthaya05@gmail.com");
	    helper.setTo(receiver);
	    String subject = "Order"+email.getOrderId();
	  
	    
	    String content = "Hello "+email.getFirstName() + " " + email.getLastName()+"\n"
	            + "Your Order Details:\n\n"
	            + "--------------------------------------------------\n"
	            + "| Delivery Address                               |\n"
	            + "--------------------------------------------------\n"
	            + "| Door Number:\t"	+"   "+ String.format("%-30s",email.getDoorNo())+"\n"
	            + "| Street Name:\t" 	+"   "+ String.format("%-30s",email.getStreetName())+"\n"
	            + "| Area:\t\t"         +"   "+ String.format("%-30s",email.getArea())+"\n"
	            + "| District:\t"       +"   "+ String.format("%-30s",email.getDistrict())+"\n"
	            + "| State:\t"          +"   "+ String.format("%-30s",email.getState())+"\n"
	            + "| Pincode:\t"        +"   "+ String.format("%-30s",email.getPincode())+"\n"
	            + "| Order Number:\t"   +"   "+ String.format("%-30s",email.getOrderId())+"\n"
	            + "| Order Placed On:"  +"   "+ String.format("%-28s",formattedDate)+"\n"
	            + "| Total Amount: "    +"   "+ String.format("%-30s","₹"+o.getTotalAmount())+"\n" 
	            + "--------------------------------------------------\n"
	            + "| Food Items and Quantity                       |\n"
	            + "--------------------------------------------------\n";

	      for(int i = 0; i < menus.size(); i++){
	    	
	    	  	Menu menu = menus.get(i);
	    	    Long q = quantity.get(i);
	    		content += "| " + String.format("%-45s",menu.getName())+"--->"+q+"\n";
	    	}
	    

	    content += "--------------------------------------------------\n";

	    helper.setSubject(subject);
	    helper.setText(content);

	   
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    Document document = new Document();
	    PdfWriter.getInstance(document, outputStream);
	    document.open();
	    document.add(new Paragraph("Welcome  to  Our restaurant"));
        document.add(new Paragraph(content));
        document.add(new Paragraph("Order Confirmed"));
	    document.close();
	    

	    DataSource attachment = new ByteArrayDataSource(outputStream.toByteArray(), "application/pdf");
	    helper.addAttachment("orderBil.pdf", attachment);

	    mailSender.send(msg);
	    
	}

	private Double calculateTotal(List<SelectedFoodDto> selectedFoods, List<Menu> menus) {
		List<Long> menuIds = selectedFoods.stream().map(SelectedFoodDto::getMenuId)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		List<Menu> menuItems = menuRepository.findByIdIn(menuIds);
		Map<Long, Menu> menuMap = menuItems.stream().
				collect(Collectors.toMap(Menu::getId, Function.identity()));
	    		Double total = 0.0d;
	    		for(SelectedFoodDto selectedFood: selectedFoods){
	    			BigDecimal amount = menuMap.get(selectedFood.getMenuId()).getAmount();
    			    total += selectedFood.getQuantity()*amount.doubleValue();
	    		}
	   	
				return total;
	 }

	
		
}
