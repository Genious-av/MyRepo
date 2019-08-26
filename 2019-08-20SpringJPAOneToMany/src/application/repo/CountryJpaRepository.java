package application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.Country;

public interface CountryJpaRepository extends JpaRepository<Country,Integer> {
	List<Country> findByName(String name);
}
