package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.entity.CarDoc;
import application.repo.CarMongoRepo;
import application.service.BaseCheckings;
import application.service.ICarRentService;
import dto.CarDTO;
import dto.DriverDTO;
import dto.ModelDTO;

@RestController
public class Controller {
	BaseCheckings check = new BaseCheckings();
	@Autowired
	ICarRentService service;
	
	
	@PostMapping("/addCar")
	public ResponseEntity<String> addCar(@RequestBody CarDTO carDto){
		service.addCar(carDto);
		return new ResponseEntity<String>("Car added",HttpStatus.OK) ;
		
	}
	
	
	@PostMapping("/addModel")
	public ResponseEntity<String> addModel(@RequestBody ModelDTO modelDTO){
		service.addModel(modelDTO);
		return new ResponseEntity<String>("Model added",HttpStatus.OK) ;
		
	}
	
	@PostMapping("/addDriver")
	public ResponseEntity<String> addDriver(@RequestBody DriverDTO driverDTO){
		service.addDriver(driverDTO);
		return new ResponseEntity<String>("Driver added",HttpStatus.OK) ;
		
	}
}
