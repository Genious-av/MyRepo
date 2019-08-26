package application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.ChildEntity;

public interface ChildJPARepo extends JpaRepository<ChildEntity , Integer>{

}
