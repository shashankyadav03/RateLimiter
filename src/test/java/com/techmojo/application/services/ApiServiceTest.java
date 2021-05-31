package com.techmojo.application.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techmojo.application.config.RateLimitConfig;

@ExtendWith(MockitoExtension.class)
public class ApiServiceTest{
	
	@Mock
	RateLimitConfig rateLimitConfig;
	
	
	@InjectMocks
	ApiService apiService;
	
	@Test
	void getResultTest() {
		String url="https://fakerapi.it/api/v1/places?_quantity=1";
		Mockito.when(rateLimitConfig.getApiurl()).thenReturn(url);
		Map<String,Object> map=apiService.getResult();
		assertNotNull(map);
		
	}
	
	@Test
	void getErrorTest() {
		
		Mockito.when(rateLimitConfig.getTime()).thenReturn((long) 90000);
		Map<String,Object> map=apiService.getError("TestUser");
		assertNotNull(map);
	}

}
