package com.trechmojo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmojo.model.Search;
import com.techmojo.model.User;
import com.techmojo.repository.IUserRepository;

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
		if(usersignup.getPassword().equals(usersignup.getRepassword())) {
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
	public Boolean processSearch(User user,Search search) {
		if(search==null)
			return false;
		if(search.getPlace().length()<3) {
				return false;
		}
		return rateLimiterService.checkRate(user);
	}

}
