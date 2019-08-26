package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.entities.GroupEntity;
import application.entities.StudentEntity;
import application.entities.TeacherEntity;
import application.service.IUniversityService;
import dto.Group;


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
	
	
	@GetMapping("/findGroupByTeacher")
	public List<GroupEntity>findGroup(@RequestParam int passportTeacher) {
		return service.findGroupByTeacher(passportTeacher);
	}
	
	
	@DeleteMapping("/removeTeacher")
	public ResponseEntity<String> removeTeacher(@RequestParam int passportTeacher) {
		return service.removeTeacher(passportTeacher);
	}
	
	
	@GetMapping("/findGroupByStudent")
	public List<GroupEntity>findGroupByStudent(@RequestParam int passportStudent) {
		return service.findGroupByname(passportStudent);
	}
	
	@GetMapping("/findTeacherByGroup")
	public List<TeacherEntity>findTeacherByGroup(@RequestParam int passportStudent) {
		return service.findTeacherByname(passportStudent);
	}
	
	@GetMapping("/findnumberofStudents")
	public int findnumberofStudents(@RequestParam String groupName) {
		return service.numberOfStudentsInGroup(groupName);
	}
	
	@GetMapping("/mostPopulatedGroup")
	public GroupEntity mostPopulatedGroup() {
		return service.mostPopulatedGroup();
	}
	
	@GetMapping("/teachersNumberOfStudents")
	public int teachersNumberOfStudents(@RequestParam int passportTeacher)  {
		return service.numberofTeachersStudents(passportTeacher);
	}
	
	@GetMapping("/studentsGreaterThen")
	public List<StudentEntity> studentsGreaterThen(@RequestParam int age)  {
		return service.greaterThenAge(age);
	}
}
