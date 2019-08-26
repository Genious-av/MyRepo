package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.service.IService;

@RestController
public class Controller {
	
	@Autowired
	IService service;
	
	@PostMapping("/addCompany")
	public String addCompany(@RequestParam String companyName) {
		service.addCompany(companyName);
		return "OK";
	}
	
	
	
	@PostMapping("/addEmployee")
	public boolean addEmployee(@RequestParam String firstName,@RequestParam String lastName,@RequestParam int age) {
		
		return service.addEmployee(firstName, lastName, age);
	}
	
	@PostMapping("/addChild")
	public boolean addChild(@RequestParam String name) {
		service.addChild(name);
		return true;
	}
	
	
	@PutMapping("/setEmployeeToCompany")
	public boolean setEmployeeToCompany(@RequestParam String firstName,@RequestParam String lastName,@RequestParam int companyID) {
		return service.setEmployeeToCompany(firstName, lastName, companyID);
	}
	
	@PutMapping("/setFatherToChild")
	public boolean setFatherToChild(@RequestParam int childId,@RequestParam String firstName, @RequestParam String lastName) {
		return service.setFatherToChild(childId, firstName, lastName);
	
	}
}
