package com.techmojo.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.trechmojo.application.services.ApiService;
import com.trechmojo.application.services.IApiService;
import com.trechmojo.application.services.IProcessUser;
import com.trechmojo.application.services.IRateLimiterService;
import com.trechmojo.application.services.ProcessUser;
import com.trechmojo.application.services.RateLimiterService;
import com.trechmojo.application.services.UserRepositoryService;

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
