package com.techmojo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.trechmojo.services.ApiService;
import com.trechmojo.services.IApiService;
import com.trechmojo.services.IProcessUser;
import com.trechmojo.services.IRateLimiterService;
import com.trechmojo.services.ProcessUser;
import com.trechmojo.services.RateLimiterService;
import com.trechmojo.services.UserRepositoryService;

@Configuration
public class ProcessConfig {

	@Bean
	public IProcessUser getProcessUser() {
		return new ProcessUser();
	}
	
	@Bean
	public UserRepositoryService userRepositoryService() {
		return new UserRepositoryService();
	}
	
	@Bean
	public IRateLimiterService rateLimiterService() {
		return new RateLimiterService();
	}
	
	@Bean
	public IApiService apiService() {
		return new ApiService();
	}
}
