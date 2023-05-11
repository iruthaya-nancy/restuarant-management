package com.example.restaurant.service;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;

public interface ForgetPasswordService {
	public void sendEmail(String recipientEmail, String link)
	        throws MessagingException, UnsupportedEncodingException;

}
