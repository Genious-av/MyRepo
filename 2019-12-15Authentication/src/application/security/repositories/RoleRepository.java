package application.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
