package com.trechmojo.application.services;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.techmojo.application.repository.IUserRepository;

@Component
public class UserRepositoryService implements IUserRepository{
	
	private HashMap<String, String> userDatabase = new HashMap<String, String>();

	@Override
	public Boolean setuser(String user, String password) {
		if(checkUser(user))
			return false;
		userDatabase.put(user,password);
		return true;
	}

	@Override
	public Boolean checkUser(String user) {
		if(userDatabase.containsKey(user))
			return true;
		return false;
	}

	@Override
	public boolean checkCredencials(String user, String password) {
		
		if(checkUser(user))
			return userDatabase.get(user).equals(password);
		
		return false;
	}

	
	
	
	
}
