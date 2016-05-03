package com.yash.yits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	@RequestMapping(value="/welcomeView")
	public String welcomePage(){
		return "Welcome";
	}
	
	@RequestMapping(value="/userWelcome")
	public String userDashboard(){
		return "UserDashboard";
	}
	
	@RequestMapping(value="/managerWelcome")
	public String managerDashboard(){
		return "ManagerDashboard";
	}
}
