package com.example.restaurant.service.impl;


import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.el.parser.AstDotSuffix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
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

	/* public Optional<Order> getOrderById(Long orderId) {
	        return order.findById(orderId);
	    }
	*/
	@Transactional
	public Long createOrder(List<SelectedFoodDto> selectedFoods, Long customerId,Long paymentid) throws BusinessServiceException, UnsupportedEncodingException,MessagingException {
//		Customer customer = new Customer();
//		Order order = new Order();
//		order.setId(orderDto.getId());
//	    order.setCustomer(new Customer(customer.getId()));
//	    order.setTotalAmount(orderDto.getTotalAmount());
//	    orderRepo.save(order);
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
//				try {
//				     //sendOrderEmail(customerId);
//			     }
//				catch(MessagingException exception) {
//					throw new BusinessServiceException("cannot mail");
//				}
//				}
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
		
//		SimpleMailMessage msg = new SimpleMailMessage();
		
		MimeMessage msg = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(msg,true);
		
	    helper.setFrom("iruthaya05@gmail.com");
	    helper.setTo(receiver);
	    String subject = "Here's your Order";
	    
	     
	    String content = "Hello,"+"\n"
	        	+ "Your Order is"+"\n"
	            +"FirstName:"+email.getFirstName()+" "+email.getLastName()+"\n"
	            +"Delivery Location"+"\n"
	            +"DooorNumber:"+email.getDoorNo()+"\n"
	            +"StreetName:"+email.getStreetName()+"\n"
	            +"Area:"+email.getArea()+"\n"
	            +"District:"+email.getDistrict()+"\n"
	    		+"State:"+email.getState()+"\n"
	    		+"Pincode:"+email.getPincode()+"\n"
	    		+"Your ordernumber:"+email.getOrderId()+"\n"
	    		+"Order placed on:"+email.getOrderdate();
	    
	    helper.setSubject(subject);
	     
//	    msg.setText(content);
	    String foodcontent = content;
	    for(Menu menu:menus) {
	    	 foodcontent += "\n"+"Food :"+menu.getName();
	    }
	    helper.setText(foodcontent);
	   
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    Document document = new Document();
	    PdfWriter.getInstance(document, outputStream);
	    document.open();
	    document.add(new Paragraph("Order details"));
	    //document.add(new Paragraph("Name:"+email.getFirstName()+email.getLastName()));
        document.add(new Paragraph(foodcontent));
//	    document.add(new Paragraph("Order total: $" + order.getTotal()));
	    document.close();
	    

	    DataSource attachment = new ByteArrayDataSource(outputStream.toByteArray(), "application/pdf");
	    helper.addAttachment("order.pdf", attachment);
//	    ByteArrayDataSource dataSource = new ByteArrayDataSource( "application/pdf");
//	    helper.addAttachment("order-"  + ".pdf", dataSource);

	    mailSender.send(msg);
	    
	}

	private Double calculateTotal(List<SelectedFoodDto> selectedFoods, List<Menu> menus) {
		// check the id from menu and selected food
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
