package com.techmojo.application.controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techmojo.application.model.Search;
import com.techmojo.application.model.User;
import com.trechmojo.application.services.IApiService;
import com.trechmojo.application.services.IProcessUser;


@Controller
public class UserRestController {   
	
	
	@Autowired
	IProcessUser processUser;
	
	@Autowired
	IApiService apiService;
	
	@GetMapping("/") 
	public String index(Model model)  {  
		model.addAttribute("user", new User());
		processUser.createAdmin();
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
	
	@PostMapping(path="user/search/{placeHolder}",consumes = "application/x-www-form-urlencoded")
    public String searchWebApp(User user, Search search,Model model) {
		if(!processUser.checkUser(user)) {
			model.addAttribute("user", new User());
			return "login-error";
		}
		
		if(!processUser.processSearch(user,search.getPlace())) {
			model.addAttribute("user", user);
			model.addAttribute("search", new Search());
			return "welcome-error";
		}
		
		model.addAttribute("user", user);
		return "agent";

    }
	
	@PostMapping(path="user/search/{place}",consumes = "application/json")
	@ResponseBody
    public ResponseEntity<Map<String, Object>> searchController(@RequestBody User user,@PathVariable("place") String place) {
		
		if(!processUser.processSearch(user,place)) {
			return new ResponseEntity<Map<String, Object>>(apiService.getError(user.getUsername()),HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<Map<String, Object>>(apiService.getResult(),HttpStatus.OK);

    }
	
	
	
	
}
