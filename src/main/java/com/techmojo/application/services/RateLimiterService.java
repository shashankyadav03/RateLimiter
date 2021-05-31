package com.techmojo.application.services;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.techmojo.application.config.RateLimitConfig;
import com.techmojo.application.model.User;

public class RateLimiterService implements IRateLimiterService {
	private HashMap<String, User> rateCounter = new HashMap<String,User>();
	
	@Autowired
	RateLimitConfig rateLimitConfig;
	
	Logger logger = LoggerFactory.getLogger(RateLimiterService.class);
	
	@Override
	public Boolean userActivity(User user) {
		
		if(rateCounter.containsKey(user.getUsername())) {
			User olduser=rateCounter.get(user.getUsername());
			if(!checkTime(olduser)) {
				olduser.setCounter(1);
				olduser.setTimeStamp(System.currentTimeMillis());
				logger.info("Counter Reset Done, Counter Value : {}, TimeStamp : {}",olduser.getCounter(),olduser.getTimeStamp());
				rateCounter.put(user.getUsername(),olduser);
			}
			else if(olduser.getCounter()>=rateLimitConfig.getLimit()) {
				olduser.setCounter(olduser.getCounter()+1);
				logger.info("Limit exceeded, Counter value : {}",olduser.getCounter());
				rateCounter.put(user.getUsername(),olduser);
				return false;
			}
			else {
				olduser.setCounter(olduser.getCounter()+1);
				rateCounter.put(user.getUsername(),olduser);
			}
			logger.info("Counter value : {}",olduser.getCounter());
		}
		else {
			user.setTimeStamp(System.currentTimeMillis());
			user.setCounter(1);
			logger.info("Counter value : {}",user.getCounter());
			rateCounter.put(user.getUsername(),user);
		}
		
		return true;
	}
	private Boolean checkTime(User user) {
		long start = user.getTimeStamp();
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
		logger.info("Elapsed Time : {} seconds",elapsedTime/1000);
		if(elapsedTime>rateLimitConfig.getTime()) {
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
