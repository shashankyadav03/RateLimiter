package com.trechmojo.application.services;

import com.techmojo.application.model.User;

public interface IProcessUser {
	
	public Boolean processSignup(User user);
	public Boolean checkUser(User user);
	public Boolean processSearch(User user,String search);
	public void createAdmin();
	
}
