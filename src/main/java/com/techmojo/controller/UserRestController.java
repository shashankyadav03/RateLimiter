package com.techmojo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.techmojo.model.Search;
import com.techmojo.model.User;
import com.trechmojo.services.IProcessUser;


@Controller
public class UserRestController {   
	
	
	@Autowired
	IProcessUser processUser;
	
	@GetMapping("/") 
	public String index(Model model)  {  
		model.addAttribute("user", new User());
		User admin = new User();
		admin.setUsername("a");
		admin.setPassword("a");
		admin.setRepassword("a");
		if(processUser.processSignup(admin)) {
			return "index";
		}
		return"index";
	}  
	
	@PostMapping("index")
    public String signup(@ModelAttribute User usersignup ,Model model) {
		model.addAttribute("user", new User());
		if(processUser.processSignup(usersignup)) {
			return "index";
		}
		
		return "login-error";
    }
	
	@PostMapping("welcome")
    public String personSubmit(@ModelAttribute User user,Model model) {
		if(!processUser.checkUser(user)) {
			model.addAttribute("user", new User());
			return "login-error";
		}
		
		model.addAttribute("user", user);
		model.addAttribute("search", new Search());
		return "welcome";
		

    }
	
	@PostMapping("agent")
    public String searchSubmit(@ModelAttribute User user,@ModelAttribute Search search,Model model) {
		if(!processUser.checkUser(user)) {
			model.addAttribute("user", new User());
			return "login-error";
		}
		
		if(!processUser.processSearch(user,search)) {
			model.addAttribute("user", user);
			model.addAttribute("search", new Search());
			return "welcome-error";
		}
		
		model.addAttribute("user", user);
		return "agent";

    }
	
	
	
	
}
