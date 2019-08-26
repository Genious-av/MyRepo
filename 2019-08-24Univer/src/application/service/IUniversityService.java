package application.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import application.entities.GroupEntity;
import application.entities.StudentEntity;
import application.entities.TeacherEntity;
import dto.Group;
@Service
public interface IUniversityService {
	ResponseEntity<String> addTeacher(int passportTeacher, String firstName, String lastName);
	ResponseEntity<String> addGroup(String groupName, String courseName, int passportTeacher);
	ResponseEntity<String> addStudent(int passportStudent, String name, int age, String groupName);
	
	ResponseEntity<String>  removeTeacher(int passportTeacher);
	
	List<GroupEntity>  findGroupByname(int passportStudent);
	List<TeacherEntity>  findTeacherByname(int passportStudent);
	
	 List<GroupEntity>  findGroupByTeacher(int passportTeacher);
	int numberOfStudentsInGroup(String groupName);
	GroupEntity mostPopulatedGroup();
	
	int numberofTeachersStudents(int passportTeacher);
	
	List<StudentEntity> greaterThenAge(int age);
	
	
}	
	
	
	
	
