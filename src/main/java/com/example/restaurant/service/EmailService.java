package com.example.restaurant.service;

public interface EmailService {
	public void sendPasswordResetToken(String email,String link);
}
