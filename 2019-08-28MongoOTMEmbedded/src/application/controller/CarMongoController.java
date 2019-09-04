package application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@GetMapping("/findByOwnerId")
	public List<CarDoc> findByOwnerId(@RequestParam int id) {
		
		return service.findByOwnerID(id);
	}
	
//	@PostMapping("/addCars")
//	public int addCars(@RequestBody ArrayList<CarDoc> cars) {
//		
//		return service.addCars(cars);
//	}
//	
//	@GetMapping("/getCarsByModel")
//		public List<CarDoc> getCarsByModel(@RequestParam String model){
//			return service.findCarsByModel(model);
//		}
//	
//	@GetMapping("/getEengineByRange")
//	public List<CarDoc> getEengineByRange(@RequestParam double engineFrom, @RequestParam double engineTo ){
//		return service.findCarsByEngineRange(engineFrom, engineTo);
//		}
//	
//	@GetMapping("/getEengineRangeQuery")
//	public List<CarDoc> getEengineRangeQuery(@RequestParam double engineFrom, @RequestParam double engineTo ){
//		return service.findCarsByQuery(engineFrom, engineTo);
//		}
//	
//	@GetMapping("/getCarsByDate")
//	public List<CarDoc> getCarsByDate(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate  dateFrom, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate  dateTo ){
//		
//		return service.findCarsByDate(dateFrom, dateTo);
//		}
//	
//	
	}
	
	
	

