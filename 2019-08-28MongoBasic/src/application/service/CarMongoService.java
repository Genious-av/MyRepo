package application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.CarDoc;
import application.repo.CarMongoRepo;

@Service
public class CarMongoService implements ICarMongoService {
	@Autowired 
	CarMongoRepo carRepo;
	
	public boolean addCar(CarDoc car) {
		if(carRepo.existsById(car.getVin())) return false;
		
		carRepo.save(car);
		return true;
	}
	
	public int addCars(List<CarDoc> cars) {
		List<CarDoc> uniqCars=cars.stream()
									.filter(car->!carRepo.existsById(car.getVin()))
									.collect(Collectors.toList());
		
		carRepo.saveAll(uniqCars);
		return uniqCars.size();
	}
	
	public List<CarDoc> findCarsByModel(String model){
		return carRepo.findByModel(model);
	}
	
	public List<CarDoc> findCarsByEngineRange(double engineFrom, double engineTo){
		return carRepo.findByEngineBetween(engineFrom, engineTo);
	}
	
	public List<CarDoc> findCarsByQuery(double engineFrom, double engineTo){
		return carRepo.findByQuery(engineFrom, engineTo);
	}
	
	public List<CarDoc> findCarsByDate(LocalDate dateFrom, LocalDate dateTo){
		return carRepo.findByProductionDateBetween(dateFrom, dateTo);
	}
}
