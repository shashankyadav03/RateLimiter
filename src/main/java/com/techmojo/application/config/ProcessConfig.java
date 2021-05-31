package com.techmojo.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.techmojo.application.repository.IUserRepository;
import com.techmojo.application.services.ApiService;
import com.techmojo.application.services.IApiService;
import com.techmojo.application.services.IProcessUser;
import com.techmojo.application.services.IRateLimiterService;
import com.techmojo.application.services.ProcessUser;
import com.techmojo.application.services.RateLimiterService;
import com.techmojo.application.services.UserRepositoryService;

@Configuration
public class ProcessConfig {

	@Bean
	public IProcessUser getProcessUser() {
		return new ProcessUser();
	}
	
	@Bean
	public IUserRepository userRepositoryService() {
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
