package application.service;

import org.springframework.beans.factory.annotation.Autowired;

import application.repo.CarMongoRepo;
import application.repo.DriverMongoRepo;
import application.repo.ModelMongoRepo;
import application.repo.RentRecordMongoRepo;

public class BaseCheckings {
	
	@Autowired
	CarMongoRepo carRepo;
	
	@Autowired
	DriverMongoRepo driverRepo;
	
	@Autowired
	ModelMongoRepo modelRepo;
	
	@Autowired
	RentRecordMongoRepo rrRepo;
	
	public boolean carExists(String carId) {
		
		
		return carRepo.findById(carId)!=null?true:false;
		
	}
	
	public boolean modelExists(String modelName) {
		
		return (modelRepo.existsById(modelName))? true:false;
		
	}
	
	public boolean driverExists(int driverID) {
		
		return (driverRepo.existsById(driverID))? true:false;
		
	}
}
