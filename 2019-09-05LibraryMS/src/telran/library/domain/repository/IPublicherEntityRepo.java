package telran.library.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.domain.entities.PublisherEntity;

public interface IPublicherEntityRepo extends JpaRepository<PublisherEntity, String>{
	List<PublisherEntity> findByCountry(String name);
	
}
