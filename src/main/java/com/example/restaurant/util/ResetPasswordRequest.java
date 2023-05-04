package com.example.restaurant.util;

public class ResetPasswordRequest {
	 private String email;
	 private String resetPasswordLink;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getResetPasswordLink() {
		return resetPasswordLink;
	}
	public void setResetPasswordLink(String resetPasswordLink) {
		this.resetPasswordLink = resetPasswordLink;
	}

	public ResetPasswordRequest() {}
	
	public ResetPasswordRequest(String email, String resetPasswordLink) {
		super();
		this.email = email;
		this.resetPasswordLink = resetPasswordLink;
	}
	
	
}
