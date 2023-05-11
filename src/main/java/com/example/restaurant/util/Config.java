package com.example.restaurant.util;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class Config {
	@Bean
    public JavaMailSender javaMailSender() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        
        return new JavaMailSenderImpl();
    }
}
