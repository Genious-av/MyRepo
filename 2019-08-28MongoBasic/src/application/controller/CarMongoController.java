package application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.entities.CarDoc;
import application.service.ICarMongoService;

@RestController
public class CarMongoController {
	@Autowired
	ICarMongoService service;
	
	@PostMapping("/addCar")
	public boolean addCar(@RequestBody CarDoc car) {
		
		return service.addCar(car);
	}
	
	@PostMapping("/addCars")
	public int addCars(@RequestBody ArrayList<CarDoc> cars) {
		
		return service.addCars(cars);
	}
	
	
	
}
