package com.techmojo.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techmojo.application.model.User;

@ExtendWith(MockitoExtension.class)
public class ProcessUserTest  {
	
	@Mock
	UserRepositoryService userRepository;
	
	@Mock
	RateLimiterService rateLimiterService;
	
	@InjectMocks
	ProcessUser processUser;

	@Test
	void processSignupTest() {
		User usersignup=new User();
		usersignup.setUsername("TestUser");
		usersignup.setPassword("pass");
		usersignup.setRepassword("pass");
		processUser.processSignup(usersignup);
		assertEquals(false,userRepository.checkUser("TestUser"));
	}

	@Test
	void checkUserTest() {
		processUser.createAdmin();
		assertEquals(false,userRepository.checkUser("admin"));
	}
	
	@Test
	void processSearchTest_Positive() {
		User user=new User();
		user.setUsername("TestUser");
		Mockito.when(rateLimiterService.checkRate(user)).thenReturn(true);
		Boolean result=processUser.processSearch(user,"Goa");
		assertEquals(true,result);
	}

	@Test
	void processSearchTest_Negative() {
		User user=new User();
		user.setUsername("TestUser");
		Mockito.when(rateLimiterService.checkRate(user)).thenReturn(false);
		Boolean result=processUser.processSearch(user,"Goa");
		assertEquals(false,result);
	}


	

}
