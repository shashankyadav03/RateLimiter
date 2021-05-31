package com.techmojo.application.services;

import com.techmojo.application.model.User;

public interface IRateLimiterService {
	
	public Boolean userActivity(User user);
	public Boolean checkRate(User user);
	public User getUser(String user);
	

}
