package application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.entities.CarEntity;
import application.exceptions.NoIdException;
import application.service.ICarService;
import dto.Car;

@RestController
public class CarController {
	
	@Autowired //give access to instance of class CarService
	ICarService service;
	
	
	@GetMapping("/addOneRandomCar")
	public boolean addOneRandomCar() {
		return service.addCar(new CarEntity(Car.getRandomCar()));
	}
	
	@GetMapping("/addManyCars/{num}")
	public String addManyCars(@PathVariable int num) {
		ArrayList<CarEntity>listCar = new ArrayList<CarEntity>();
		for(int i=0;i<num;i++) {
			listCar.add(new CarEntity(Car.getRandomCar()));
			service.addManyCars(listCar);
		}
		return "OK";
		
	}
	
	@GetMapping("/getCarById")
	public Car getCarById(@RequestParam ("id") int id) throws NoIdException  { //ResponseEntity used to send response to server
		CarEntity result = service.getCarById(id);
		if(result==null) throw new  NoIdException("No id");
		return result.getCar();
		
		
	}
	
	@GetMapping("/getAllCars")
	public List<Car> getCarById(){
		
		return listToList(service.getAllCars());
	}
	
	
	@GetMapping("/getCarsByModel")
	public List<Car> getCarById(@RequestParam("model") String model){
		
		return listToList(service.getCarsByModel(model));
	}
	
	@GetMapping("/getCarsByEngineRange")
	public List<Car> getCarsByEngineRange(@RequestParam("from") double from, @RequestParam("to") double to){
		
		return listToList(service.getCarsByEngineRange(from, to));
	}
	
	
	/* variant with parsing string to localdata
	@GetMapping("/getCarsByProductionDateRange")
	public List<Car> getCarsByProductionDateRange(@RequestParam("from") String from, @RequestParam("to") String to){
		
		return listToList(service.getCarsByProductionDate(LocalDate.parse(from), LocalDate.parse(to)));
	}
	*/
	
	@GetMapping("/getCarsByProductionDateRange")
	public List<Car> getCarsByProductionDateRange(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate from, 
													@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate to){
		
		return listToList(service.getCarsByProductionDate(from, to));
	}
	
	@GetMapping("/getCarsByQuery")
	public List<Car> getCarsByQuery(){
		
		return listToList(service.getCarsByByQuery());
	}
	
	@GetMapping("/getCarsEngineByQuery")
	public List<Car> getCarsEngineByQuery(@RequestParam("from") double from, @RequestParam("to") double to){
		
		return listToList(service.findEngineByQuery(from, to));
	}
	
	
	
	private  List<Car> listToList(List<CarEntity> list){
		return list.stream().map(CarEntity::getCar).collect(Collectors.toList()); //convert from CarEntity - to Car
	}
	
	
	@GetMapping("/getIdByCar")
	public List<CarEntity> getIdByCar(@RequestParam("model") String model,@RequestParam("productionDate") String productionDate,  @RequestParam("engine") double engine,  @RequestParam("ac") boolean ac){
		return service.findIdByCar(model, LocalDate.parse(productionDate), engine, ac);
		
	}
	
	
}
