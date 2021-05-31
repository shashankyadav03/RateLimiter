package com.techmojo.application.repository;

public interface IUserRepository {
	
	public Boolean setuser(String user,String password);
	
	public Boolean checkUser(String user);
	
	public boolean checkCredencials(String user,String password);
	
}
