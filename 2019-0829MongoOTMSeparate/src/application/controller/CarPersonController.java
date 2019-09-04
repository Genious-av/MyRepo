package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.service.ICarPerson;
import dto.CarDto;
import dto.PersonDto;

@RestController
public class CarPersonController {

	@Autowired
	ICarPerson service;
	
	@PostMapping("/addOwner")
	public boolean addOwner(@RequestBody PersonDto person ) {
		return service.addOwner(person);
	}
	
	@PostMapping("addCar")
	public boolean addCar(@RequestBody CarDto car) {
		return service.addCar(car);
		
	}
	
	@GetMapping("/findOwnersCars")
	public List<CarDto> findOwnersCars(@RequestParam int id){
		return service.getOwnerCars(id);
	}
	
	
	@GetMapping("/getAllCars")
	public List<CarDto> getAllCars(){
		return service.getAllCars();
	}
	
	@GetMapping("/getAllOwners")
	public List<PersonDto> getAllOwners(){
		return service.getAllOwners();
	}
	
	@DeleteMapping("/removeCar")
	public CarDto removeCar(@RequestParam String id) {
		return service.removeCar(id);
		
	}
	
	@DeleteMapping("/removeOwner")
	public PersonDto removeOwner(@RequestParam int id) {
		return service.removePerson(id);
		
	}
	
	
	
}
