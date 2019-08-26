package application.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public interface IUniversityService {
	ResponseEntity<String> addTeacher(int passportTeacher, String firstName, String lastName);
	ResponseEntity<String> addGroup(String groupName, String courseName, int passportTeacher);
	ResponseEntity<String> addStudent(int passportStudent, String name, int age, String groupName);
	
	boolean removeTeacher(String firstName, String lastName);
	
}	
	
	
	
	
