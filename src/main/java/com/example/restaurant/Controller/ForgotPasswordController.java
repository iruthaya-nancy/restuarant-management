package com.example.restaurant.Controller;

import java.io.UnsupportedEncodingException;

import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.exception.UserNotFoundException;
import com.example.restaurant.model.Customer;
import com.example.restaurant.service.impl.CustomerServiceImpl;
import com.example.restaurant.service.impl.ForgetPasswordServiceImpl;
import com.example.restaurant.util.Utility;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ForgotPasswordController {


	@Autowired
	private CustomerServiceImpl customerService;

	@GetMapping("/forgot_password")
	public String showForgotPasswordForm() {
		return "forgot_password_form";

	}

	@PostMapping("/forgot_password")
	public String processForgotPassword(Model model, HttpServletRequest request) {
		ForgetPasswordServiceImpl passwordService= new ForgetPasswordServiceImpl();
		String email = request.getParameter("email");
		String token = RandomString.make(30);

		try {
			customerService.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
			passwordService.sendEmail(email, resetPasswordLink);
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

		} catch (UserNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error while sending email");
		}

		return "forgot_password_form";
	}

	public void sendEmail() {

	}
	
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@RequestParam(value = "toker")String token,Model model) {
		Customer customer = customerService.getByResetPasswordToken(token);
	    model.addAttribute("token", token);
	    if (customer == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    }
	    return "reset_passsword_form";// form name
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		Customer customer = customerService.getByResetPasswordToken(token);
		model.addAttribute("title", "Reset your password");

		if (customer == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		} else {
			customerService.updatePassword(customer, password);

			model.addAttribute("message", "You have successfully changed your password.");
		}

		return "message";
	}
	
	

}
