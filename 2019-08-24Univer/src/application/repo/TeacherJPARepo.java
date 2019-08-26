package application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.TeacherEntity;




public interface TeacherJPARepo extends JpaRepository<TeacherEntity, Integer> {
	List<TeacherEntity> findByPassportTeacherE(int passportTeacher);
}
