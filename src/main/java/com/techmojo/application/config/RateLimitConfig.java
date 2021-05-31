package com.techmojo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfig {
	
	@Value("${rateLimiter.limit}")
	private Integer limit;
	
	@Value("${rateLimiter.time}")
	private long time;
	
	@Value("${rateLimiter.api.url}")
	private String Apiurl; 

	public String getApiurl() {
		return Apiurl;
	}

	public void setApiurl(String apiurl) {
		Apiurl = apiurl;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	
}
