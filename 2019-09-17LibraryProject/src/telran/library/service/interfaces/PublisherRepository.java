package telran.library.service.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.domain.entities.PublisherEntity;

public interface PublisherRepository extends JpaRepository<PublisherEntity, String> {
	List<PublisherEntity> findByCountry(String countryName);
}
