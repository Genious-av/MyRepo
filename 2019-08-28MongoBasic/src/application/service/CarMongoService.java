package application.service;

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
}
