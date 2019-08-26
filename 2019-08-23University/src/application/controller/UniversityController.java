package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import application.service.IUniversityService;


@RestController
public class UniversityController {
	
	@Autowired
	IUniversityService service;
	
	
	@PostMapping("/addTeacher")
	public ResponseEntity<String> addCountry(@RequestParam int passportTeacher, @RequestParam String firstName, @RequestParam  String lastName ) {
		return service.addTeacher(passportTeacher,firstName, lastName);
	}
	
	@PostMapping("/addGroup")
	public ResponseEntity<String> addGroup(@RequestParam String groupName, @RequestParam String courseName, @RequestParam int passportTeacher) {
		return service.addGroup(groupName, courseName,passportTeacher);
	}
	
	
	@PostMapping("/addStudent")
	public ResponseEntity<String> addStudent(@RequestParam int passportStudent, @RequestParam String name,@RequestParam int age , @RequestParam String groupName) {
		return service.addStudent(passportStudent, name, age, groupName);
	}
	
}
