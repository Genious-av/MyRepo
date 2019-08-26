package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.GroupEntity;
import application.entities.TeacherEntity;

public interface GroupJPARepo extends JpaRepository<GroupEntity, Integer>{
	List<GroupEntity> findByNameGroupEntity(String nameGroup);
}
