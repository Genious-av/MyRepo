package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.GroupEntity;
import application.entities.StudentEntity;
import application.entities.TeacherEntity;

public interface StudentJPARepo extends JpaRepository<StudentEntity, Integer>{
	List<StudentEntity> findByNameGroupEntity(String nameGroup);
}
