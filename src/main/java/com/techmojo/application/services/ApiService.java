package com.techmojo.application.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmojo.application.config.RateLimitConfig;
import com.techmojo.application.model.User;


public class ApiService implements IApiService {
	
	@Autowired
	RateLimitConfig rateLimitConfig;
	
	@Autowired
	IRateLimiterService rateLimiterService;
	
	@Override
	public Map<String, Object> getResult() {
		Map<String, Object> result=null;
		try {
			URL url = new URL(rateLimitConfig.getApiurl());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream inputStream = connection.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(inputStream, Map.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	private long remainingTime(String user) {
		User currentUser=rateLimiterService.getUser(user);
		long start = currentUser.getTimeStamp();
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
		long remaining= rateLimitConfig.getTime()-elapsedTime;
		return remaining;
	}
	private String convertTominutes(long time) {
		long second = time%60000;
		long minutes = time/60000;
		return Long.toString(minutes)+"."+Long.toString(second/6000)+" minute";
		
	}
	@Override
	public Map<String, Object> getError(String user) {
		Map<String,Object> map = new HashMap<>();
		//You can convert any Object.
		map.put("Error","Search Restricted Due to maximum limit reached" );
		map.put("User", user);
		String rate=convertTominutes(rateLimitConfig.getTime());
		map.put("Rate", rateLimitConfig.getLimit().toString()+" per "+rate);
		String timeleft=convertTominutes(remainingTime(user));
		map.put("Time Remaining", timeleft);
		return map;
	}

}
