package application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.EmployeeEntity;
import application.entities.EmployeeId;

public interface EmployeeJPARepo extends JpaRepository<EmployeeEntity, EmployeeId> {

}
