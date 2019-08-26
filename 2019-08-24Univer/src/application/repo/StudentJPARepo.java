package application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import application.entities.GroupEntity;
import application.entities.StudentEntity;


public interface StudentJPARepo extends JpaRepository<StudentEntity, Integer>{
	List<StudentEntity> findByPassportStudentE(int passportStudent);
	List<StudentEntity> findByGroupEntity(GroupEntity groupEntity);
	@Query(value="SELECT*FROM student WHERE agee>?1",nativeQuery=true) 
	List<StudentEntity> findByQuery(int age);
	
	
}
