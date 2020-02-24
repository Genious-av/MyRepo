package application.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ApplicationController {
		
	@GetMapping("/admin")
	@Secured({"ADMIN"})
	public String admin() {
		return "admin";
	}
	
	
	@GetMapping("/manager")
	@Secured({"ADMIN","MANAGER"})
	public String manager() {
		return "manager";
	}
	@Secured({"ADMIN","MANAGER","USER"})
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/everybody")
	@Secured({"ADMIN","MANAGER","USER"})
	public String everybody() {
		return "everybody";
	}
	

}
