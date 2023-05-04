package com.example.restaurant.Controller;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.model.Customer;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.service.CustomerService;
import com.example.restaurant.service.EmailService;
import com.example.restaurant.service.ForgetPasswordService;
import com.example.restaurant.util.ResetPasswordRequest;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/reset")
public class ResetPasswordController {
	
	@Autowired
    private CustomerRepository  customerRepo;

	@Autowired
	private CustomerService  customerService;
	
    @Autowired
    private ForgetPasswordService emailService;

    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws UnsupportedEncodingException, MessagingException {
        Customer user = customerRepo.findByEmail(resetPasswordRequest.getEmail());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        String token = UUID.randomUUID().toString();
        customerService.createPasswordResetTokenForUser(token,resetPasswordRequest.getEmail());
        String resetPasswordLink = resetPasswordRequest.getResetPasswordLink() + "?token=" + token;
        emailService.sendEmail(user.getEmail(), resetPasswordLink);
        return ResponseEntity.ok().body("Reset password link has been sent to your email");
    }
}
