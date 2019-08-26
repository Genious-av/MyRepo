package application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.AuthorEntity;

public interface AuthorJPARepo extends JpaRepository<AuthorEntity, String>{

	

}
