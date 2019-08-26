package application.model;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.City;
import application.entities.Country;
import application.repo.CityJpaRepository;
import application.repo.CountryJpaRepository;

@Service
public class OTM_Model implements I_OTM_Model{
	
	@Autowired
	CountryJpaRepository countryRepo;
	
	@Autowired
	CityJpaRepository cityRepo;

	public boolean addCountry(String name) {
		if (countryRepo.findByName(name).size() > 0) return false;
		countryRepo.save(new Country(name));
		return true;
	}
	
	public boolean addCity(String city_name, String country_name ) {
		List<Country> countryFound = countryRepo.findByName(country_name);
		Country country = countryFound.size() == 0 ? countryRepo.save(new Country(country_name)) : countryFound.get(0);
		cityRepo.save(new City(city_name, country));
		
		return true;
	}
	
	public boolean deleteCountry(int id) {
		Country country = countryRepo.findById(id).get();
		if (country == null) return false;
		countryRepo.delete(country);
		return true;
	}
	
	public Country getCountry(String city_name) {
		List<City> found = cityRepo.findByName(city_name);
		return found.size() == 0 ? null : found.get(0).getCountry();
	}
	
	public  Set<City> getSities(String country_name) {
		List<Country> found = countryRepo.findByName(country_name);
		return found.size() == 0 ? null : found.get(0).getCities();
	}
}
