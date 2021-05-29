package com.techmojo.model;

public class User {
	
	private String username;
	private String password;
	private String repassword;
	private String email;
	private long timeStamp;
	private Integer counter;
	
	
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
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long l) {
		this.timeStamp = l;
	}
	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	
	
	

}
