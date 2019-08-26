package application.model;

import java.util.Set;

import application.entities.City;
import application.entities.Country;

public interface I_OTM_Model {

	public boolean addCountry(String name);
	public boolean addCity(String city_name, String country_name );
	public boolean deleteCountry(int id);
	public Country getCountry(String name);
	public  Set<City> getSities(String country_name);
}
