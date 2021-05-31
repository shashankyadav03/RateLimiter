package com.trechmojo.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmojo.application.model.Search;
import com.techmojo.application.model.User;
import com.techmojo.application.repository.IUserRepository;

@Service
public class ProcessUser implements IProcessUser {
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IRateLimiterService rateLimiterService;

	@Override
	public Boolean processSignup(User usersignup) {
		if(usersignup==null)
			return false;
		if(usersignup.getPassword().length()>1&&usersignup.getPassword().equals(usersignup.getRepassword())) {
			return userRepository.setuser(usersignup.getUsername(), usersignup.getPassword());
		}
		return false;
	}

	@Override
	public Boolean checkUser(User user) {
		if(user==null)
			return false;
		return userRepository.checkCredencials(user.getUsername(),user.getPassword());
	}

	@Override
	public void createAdmin() {
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setRepassword("admin");
		processSignup(admin);
	}
	
	@Override
	public Boolean processSearch(User user,String place) {
		if(place==null||user==null)
			return false;
		if(place.length()<3) {
				return false;
		}
		return rateLimiterService.checkRate(user);
	}

	

}
