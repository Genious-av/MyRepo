package application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.City;
import application.entities.Country;

public interface CityJpaRepository extends JpaRepository<City, Integer>{
	List<City> findByName(String name);
}