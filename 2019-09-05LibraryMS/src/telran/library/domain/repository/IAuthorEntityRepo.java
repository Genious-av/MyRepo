package telran.library.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.domain.entities.AuthorEntity;

public interface IAuthorEntityRepo extends JpaRepository<AuthorEntity, String>{
	List<AuthorEntity> findByCountry(String country);
}
