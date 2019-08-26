package application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import application.entities.GroupEntity;
import application.entities.StudentEntity;
import application.entities.TeacherEntity;
import application.repo.GroupJPARepo;
import application.repo.StudentJPARepo;
import application.repo.TeacherJPARepo;
import dto.Group;
import dto.Teacher;


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
		List<TeacherEntity> teacherFound=teacherRepo.findByPassportTeacherE(passportTeacher);
		if(teacherFound.size()!=0) return new ResponseEntity<String>("Teacher exists", HttpStatus.FOUND);
		teacherRepo.save(new TeacherEntity(passportTeacher, firstName, lastName));
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}


	@Override
	public ResponseEntity<String> addGroup(String groupName, String courseName, int passportTeacher) {
		List<GroupEntity> groupFound=groupRepo.findByNameGroupE(groupName);
		if(groupFound.size()!=0) return new ResponseEntity<String>("Group exists", HttpStatus.FOUND);
		List<TeacherEntity> teacherFound=teacherRepo.findByPassportTeacherE(passportTeacher);
		if(teacherFound.size()==0) return new ResponseEntity<String>("Teacher doesn`t exist", HttpStatus.FOUND);
		groupRepo.save(new GroupEntity(groupName, courseName, teacherRepo.findByPassportTeacherE(passportTeacher).get(0)));
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	
	}

	@Override
	public ResponseEntity<String> addStudent(int passportStudent, String name, int age, String groupName){
		List<StudentEntity> studentFound=studentRepo.findByPassportStudentE(passportStudent);
		if(studentFound.size()!=0) return new ResponseEntity<String>("Student exists", HttpStatus.FOUND);
		List<GroupEntity> groupFound=groupRepo.findByNameGroupE(groupName);
		if(groupFound.size()==0) return new ResponseEntity<String>("Group doesn`t exist", HttpStatus.FOUND);
		studentRepo.save(new StudentEntity(passportStudent, name, age, groupRepo.findByNameGroupE(groupName).get(0)));
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> removeTeacher(int passportTeacher) {
		List<TeacherEntity> teachersFound=teacherRepo.findByPassportTeacherE(passportTeacher);
		if(teachersFound.size()==0) return new ResponseEntity<String>("Teacher doesn`t exist", HttpStatus.NO_CONTENT);
		if(findGroupByTeacher(passportTeacher).size()!=0) return new ResponseEntity<String>("Teacher has active groups", HttpStatus.FORBIDDEN);
		teacherRepo.delete(teacherRepo.findByPassportTeacherE(passportTeacher).get(0));
		 return new ResponseEntity<String>("Teacher has been removed", HttpStatus.OK);
	}

	@Override
	public List<GroupEntity> findGroupByname(int passportStudent) {
		List<StudentEntity> studentFound=studentRepo.findByPassportStudentE(passportStudent);
		if(studentFound.size()==0) return null;
		List<GroupEntity> groups=groupRepo.findByNameGroupE(studentFound.get(0).getGroupEntity().getNameGroupE());
		return groups;
	}


	@Override
	public List<TeacherEntity> findTeacherByname(int passportStudent) {
		List<StudentEntity> studentFound=studentRepo.findByPassportStudentE(passportStudent);
		if(studentFound.size()==0) return null;
		List<GroupEntity> groups=groupRepo.findByNameGroupE(studentFound.get(0).getGroupEntity().getNameGroupE());
		List<TeacherEntity> teachers=teacherRepo.findByPassportTeacherE(groups.get(0).getTeacher().getPassportTeacherE());
		return teachers;
	}


	@Override
	public List<GroupEntity> findGroupByTeacher(int passportTeacher) {
		
		List<TeacherEntity> teachersFound=teacherRepo.findByPassportTeacherE(passportTeacher);
		List<GroupEntity> groupsFound=groupRepo.findByTeacherE(teacherRepo.findByPassportTeacherE(passportTeacher).get(0));
		if(teachersFound.size()==0) return groupsFound;
				
		return groupsFound;
	}

	@Override
	public int numberOfStudentsInGroup(String groupName) {
		List<GroupEntity> groupFound=groupRepo.findByNameGroupE(groupName);
		if(groupFound.size()==0) return -1;
	
		GroupEntity groupE = groupRepo.findByNameGroupE(groupName).get(0);
		List<StudentEntity> students=studentRepo.findByGroupEntity(groupE);
	
				
		return students.size();
	}


	@Override
	public GroupEntity mostPopulatedGroup() {
		List<GroupEntity> groups=groupRepo.findAll();
		int maxNumGroup=numberOfStudentsInGroup(groups.get(0).getNameGroupE());
		GroupEntity maxGroup=groups.get(0)	;
		for(GroupEntity groupE: groups) {
			if(maxNumGroup<numberOfStudentsInGroup(groupE.getNameGroupE())){
				maxNumGroup=numberOfStudentsInGroup(groupE.getNameGroupE());
				maxGroup=groupE;
			}
		}
		return maxGroup;
	}


	@Override
	public int numberofTeachersStudents(int passportTeacher) {
		List<GroupEntity> groups=findGroupByTeacher(passportTeacher);
		int numberOfStudents=0;
		for(GroupEntity groupE: groups) {
			numberOfStudents=numberOfStudents+numberOfStudentsInGroup(groupE.getNameGroupE());
		}
		
		return numberOfStudents;
	}


	@Override
		public List<StudentEntity> greaterThenAge(int age){
		
			return studentRepo.findByQuery(age);
	}
	
	
}
