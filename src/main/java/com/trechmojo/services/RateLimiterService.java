package com.trechmojo.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;

import com.techmojo.model.User;

public class RateLimiterService implements IRateLimiterService {
	private HashMap<String, User> rateCounter = new HashMap<String,User>();
	
	@Value("${rateLimiter.limit}")
	private Integer limit;
	
	@Override
	public Boolean userActivity(User user) {
		
		if(rateCounter.containsKey(user.getUsername())) {
			User olduser=rateCounter.get(user.getUsername());
			if(!checkTime(olduser)) {
				olduser.setCounter(1);
				olduser.setTimeStamp(System.currentTimeMillis());
				rateCounter.put(user.getUsername(),olduser);
			}
			else if(olduser.getCounter()>=limit) {
				olduser.setCounter(olduser.getCounter()+1);
				rateCounter.put(user.getUsername(),olduser);
				return false;
			}
			else {
				olduser.setCounter(olduser.getCounter()+1);
				rateCounter.put(user.getUsername(),olduser);
			}
		}
		else {
			user.setTimeStamp(System.currentTimeMillis());
			user.setCounter(1);
			rateCounter.put(user.getUsername(),user);
		}
		return true;
	}
	private Boolean checkTime(User user) {
		long start = user.getTimeStamp();
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
		if(elapsedTime>10000) {
			return false;
		}
		return true;
	}
	@Override
	public Boolean checkRate(User user) {
		if(user==null)
			return false;
		return userActivity(user);
	}

}
