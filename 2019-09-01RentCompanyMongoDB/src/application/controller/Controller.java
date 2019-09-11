package application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.entity.CarDoc;
import application.entity.RentRecordDoc;
import application.repo.CarMongoRepo;
import application.service.BaseCheckings;
import application.service.ICarRentService;
import dto.CarDTO;
import dto.DriverDTO;
import dto.ModelDTO;
import dto.RentRecordDTO;

@RestController
public class Controller {
	BaseCheckings check = new BaseCheckings();
	@Autowired
	ICarRentService service;
	
	
	@PostMapping("/addCar")
	public ResponseEntity<String> addCar(@Valid @RequestBody CarDTO carDto){
		service.addCar(carDto);
		return new ResponseEntity<String>("Car added",HttpStatus.OK) ;
		
	}
	
	
	@PostMapping("/addModel")
	public ResponseEntity<String> addModel(@Valid @RequestBody ModelDTO modelDTO){
		service.addModel(modelDTO);
		return new ResponseEntity<String>("Model added",HttpStatus.OK) ;
		
	}
	
	@PostMapping("/addDriver")
	public ResponseEntity<String> addDriver(@Valid @RequestBody DriverDTO driverDTO){
		service.addDriver(driverDTO);
		return new ResponseEntity<String>("Driver added",HttpStatus.OK) ;
	}
	
	@PostMapping("/rentACar")
	public ResponseEntity<String> rentACar(@Valid @RequestBody RentRecordDTO rrDTO){
	
		return new ResponseEntity<String>(service.rentACar(rrDTO),HttpStatus.OK) ;
	}
	
	
	@PostMapping("/returnCar")
	public ResponseEntity<String> returnCar(@RequestParam String carID, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate returnDate, @RequestParam int tankPercent) {
		
		return new ResponseEntity<String>(service.returnCar(carID, returnDate,tankPercent),HttpStatus.OK) ;
	}
	
	@DeleteMapping("/deleteModel")
	public ResponseEntity<String> deleteModel(@RequestParam String modelID){
		service.removeModel(modelID);
		return new ResponseEntity<String>("Model deleted successfully",HttpStatus.OK) ;
	}
	
	@DeleteMapping("/deleteDriver")
	public ResponseEntity<String> deleteDriver(@RequestParam int driverID){
		service.removeDriver(driverID);
		return new ResponseEntity<String>("Driver deleted successfully",HttpStatus.OK) ;
	}
	
	@GetMapping("/getAllCarsByDriver")
	public ResponseEntity<List<CarDTO>> getAllCarsByDriver(@RequestParam int driverID){
		
		return new ResponseEntity<List<CarDTO>>(service.getAllCarsByDriver(driverID),HttpStatus.OK) ;
	}
	
	@GetMapping("/getAllrecordsByDriver")
	public ResponseEntity<List<RentRecordDTO>> getAllrecordsByDriver(@RequestParam int driverID){
		
		return new ResponseEntity<List<RentRecordDTO>>(service.getAllrecordsByDriver(driverID),HttpStatus.OK) ;
	}
	
	@GetMapping("/getAllModelsByDriver")
	public ResponseEntity<List<ModelDTO>> getAllModelsByDriver(@RequestParam int driverID){
			return new ResponseEntity<List<ModelDTO>>(service.getAllModelsByDriver(driverID),HttpStatus.OK) ;
	}
	
	@GetMapping("/getAllFreeCars")
	public ResponseEntity<List<CarDTO>> getAllFreeCars(){
			return new ResponseEntity<List<CarDTO>>(service.getAllFreeCars(),HttpStatus.OK) ;
	}
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	
    public List<String> handleBodyException(MethodArgumentNotValidException exception) {
		BindingResult bindingResult=exception.getBindingResult();
		List<String> result = new ArrayList<String>();
		result.addAll(bindingResult.getFieldErrors().stream().map((e)-> e.getDefaultMessage()+":"+ e.getRejectedValue())
															.collect(Collectors.toList()));
		
				 
		result.addAll(bindingResult.getAllErrors().stream()
															.filter(e->!e.getClass().equals(FieldError.class))
															.map((e)-> e.getDefaultMessage())
															.collect(Collectors.toList()));
		return result;
    }
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<String> handleParamException(ConstraintViolationException exception) {
		
		return exception.getConstraintViolations().stream()
				.map((e)-> e.getMessage()+": "+e.getInvalidValue())
				.collect(Collectors.toList());   
	}
}
