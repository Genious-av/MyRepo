package application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.security.services.ISecurityService;

@RestController
@RequestMapping("/security")
@CrossOrigin
public class SecurityController {
	
	@Autowired ISecurityService service;
	
	@PostMapping("/addAccount")
	public boolean addAccount(@RequestParam String login,
			                  @RequestParam String psw,
			                  @RequestParam String roleString) {
		return service.addAccount(login, psw, roleString);
	};
	
	@DeleteMapping("/removeAccount")
	public String removeAccount(@RequestParam String login) {
		service.removeAccount(login);
		return "Account "+login+" removed";
	}
	
	@PostMapping("/addRole")
	public boolean addRole(@RequestParam String roleString) {
		return service.addRole(roleString);
	};
	
	@PostMapping("/grantRole")
	public boolean grantRole(@RequestParam String login, @RequestParam String roleString) {
		return service.grantRole(login, roleString);
	};
	
	@PostMapping("/depriveRole")
	public boolean depriveRole(@RequestParam String login, @RequestParam String roleString) {
		return service.depriveRole(login, roleString);
	};
	
	
	@GetMapping("/getRoles")
	public String[] getRoles(@RequestParam String login) {
		return service.getRoles(login);
	};
	
	@GetMapping("/getLogins")
	public String[] getLogins(@RequestParam String roleString) {
		return service.getLogins(roleString);
	};

}
