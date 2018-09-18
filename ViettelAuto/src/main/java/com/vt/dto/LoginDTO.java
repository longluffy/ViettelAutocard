package com.vt.dto;

public class LoginDTO {
	private String username;
	private String password;
	private LoginMethodEnum category;
	private boolean hasLoginLogout; 
	 
	public LoginDTO(String username, String password, LoginMethodEnum category) {
		super();
		this.username = username;
		this.password = password;
		this.category = category;
		this.hasLoginLogout = true;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginMethodEnum getCategory() {
		return category;
	}
	public void setCategory(LoginMethodEnum category) {
		this.category = category;
	}
	public boolean isHasLoginLogout() {
		return hasLoginLogout;
	}
	public void setHasLoginLogout(boolean hasLoginLogout) {
		this.hasLoginLogout = hasLoginLogout;
	}
	
}
