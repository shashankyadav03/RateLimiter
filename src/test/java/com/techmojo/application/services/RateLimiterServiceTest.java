package com.techmojo.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techmojo.application.config.RateLimitConfig;
import com.techmojo.application.model.User;

@ExtendWith(MockitoExtension.class)
public class RateLimiterServiceTest  {
	
	@Mock
	RateLimitConfig rateLimitConfig;
	
	@InjectMocks
	RateLimiterService rateLimiterService;
	
	
	@Test
	void userActivityTest() {
		User user=new User();
		user.setUsername("TestUser");
		Boolean result=rateLimiterService.userActivity(user);
		assertEquals(true,result);
	}
	
	@Test
	void checkRateTest_Positive() {
		User user=new User();
		user.setUsername("TestUser");
		rateLimiterService.checkRate(user);
		Mockito.when(rateLimitConfig.getLimit()).thenReturn(2);
		Mockito.when(rateLimitConfig.getTime()).thenReturn((long) 90000);
		Boolean result=rateLimiterService.checkRate(user);
		assertEquals(true,result);
	}

	@Test
	void checkRateTest_Negative() {
		User user=new User();
		user.setUsername("TestUser");
		rateLimiterService.checkRate(user);
		rateLimiterService.checkRate(user);
		Mockito.when(rateLimitConfig.getLimit()).thenReturn(2);
		Mockito.when(rateLimitConfig.getTime()).thenReturn((long) 90000);
		Boolean result=rateLimiterService.checkRate(user);
		assertEquals(false,result);
	}

}
