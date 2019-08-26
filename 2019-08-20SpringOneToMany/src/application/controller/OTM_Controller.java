package application.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.entities.City;
import application.entities.Country;
import application.model.I_OTM_Model;

@RestController
public class OTM_Controller {
	
	@Autowired
	I_OTM_Model model;
	
	@PostMapping(value="/addCountry")
	public boolean addCountry(@RequestParam(value="country") String name) {
		return model.addCountry(name);
	}
	
	@PostMapping(value="/addCity")
	public boolean addCity(@RequestParam(value="city") String city_name, 
			                  @RequestParam(value="country") String country_name) {
		return model.addCity(city_name, country_name);
	}
	
	@PostMapping(value="/deleteCountry")
	public boolean deleteCountry(@RequestParam(value="id") int id) {
		return model.deleteCountry(id);
	}
	
	@GetMapping(value="/getCountry")
	public Country getCountry(@RequestParam(value="city") String city_name) {
		return model.getCountry(city_name);
	}
	
	@GetMapping(value="/getCities")
	public Set<City> getCities(@RequestParam(value="country") String country_name) {
		return model.getSities(country_name);
	}
	
	

}
