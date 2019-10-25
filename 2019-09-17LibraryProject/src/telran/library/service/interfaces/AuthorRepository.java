package telran.library.service.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.domain.entities.AuthorEntity;


public interface AuthorRepository extends JpaRepository<AuthorEntity, String> {
	List<AuthorEntity> findByCountry(String countryName);
}
