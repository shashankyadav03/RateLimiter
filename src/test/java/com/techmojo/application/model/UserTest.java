package com.techmojo.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	void userNameTest() {
		User user=new User();
		user.setUsername("Admin");
		user.setPassword("pass");
		user.setCounter(1);
		assertEquals(user.getUsername(),"Admin");
		assertEquals(user.getPassword(),"pass");
		assertEquals(user.getCounter(),1);
	}

}
