package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import application.entities.GroupEntity;
import application.entities.StudentEntity;
import application.entities.TeacherEntity;
import application.repository.GroupJPARepo;
import application.repository.StudentJPARepo;
import application.repository.TeacherJPARepo;
@Service
public class UniversityService implements IUniversityService{

	@Autowired
	StudentJPARepo studentRepo;
	
	@Autowired
	GroupJPARepo groupRepo;
	
	@Autowired
	TeacherJPARepo teacherRepo;
	
	
	
	@Override
	public ResponseEntity<String>  addTeacher(int passportTeacher, String firstName, String lastName) {
		List<TeacherEntity> teacherFound=teacherRepo.findByPassportTeacherEntity(passportTeacher);
		if(teacherFound.size()!=0) return new ResponseEntity<String>("Teacher exists", HttpStatus.FOUND);
		teacherRepo.save(new TeacherEntity(passportTeacher, firstName, lastName));
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addGroup(String groupName, String courseName, int passportTeacher) {
		List<GroupEntity> groupFound=groupRepo.findByNameGroupEntity(groupName);
		if(groupFound.size()!=0) return new ResponseEntity<String>("Group exists", HttpStatus.FOUND);
		List<TeacherEntity> teacherFound=teacherRepo.findByPassportTeacherEntity(passportTeacher);
		if(teacherFound.size()==0) return new ResponseEntity<String>("Teacher doesn`t exist", HttpStatus.FOUND);
		groupRepo.save(new GroupEntity(groupName, courseName, passportTeacher));
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	
	}


	

	@Override
	public boolean removeTeacher(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	@Override
	public ResponseEntity<String> addStudent(int passportStudent, String name, int age, String groupName){
		List<StudentEntity> studentFound=studentRepo.findByNameGroupEntity(groupName);
		if(studentFound.size()!=0) return new ResponseEntity<String>("Teacher exists", HttpStatus.FOUND);
		List<GroupEntity> groupFound=groupRepo.findByNameGroupEntity(groupName);
		if(groupFound.size()==0) return new ResponseEntity<String>("Group doesn`t exist", HttpStatus.FOUND);
		studentRepo.save(new StudentEntity(passportStudent, name, age, groupName));
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	
}
