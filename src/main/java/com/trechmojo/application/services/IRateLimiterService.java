package com.trechmojo.services;

import com.techmojo.model.User;

public interface IRateLimiterService {
	
	public Boolean userActivity(User user);
	public Boolean checkRate(User user);
	

}
