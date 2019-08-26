package application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import application.entities.CarEntity;
import application.repo.CarjpaRepository;

@Service
public class CarService implements ICarService{
	// @Autowired uses interface to creates tables. WILL NOT WORK WITHOUT CREATING INTERFACE
	@Autowired //implements interface CarjpaRepository to DB  - creates table in DB
	CarjpaRepository carRepo; //variable of type interface CarjpaRepository which is implemented by JpaRepository<CarEntity, Integer>
	
	public boolean addCar(CarEntity car) {
		if (carRepo.existsById(car.getId())) return false;
		carRepo.save(car);
		return true;
		
	}

	@Override
	public void addManyCars(List<CarEntity> listCar) {
		carRepo.saveAll(listCar);
		
	}

	@Override
	public CarEntity getCarById(int id) {
		
		return carRepo.findById(id).orElse(null);
	}

	@Override
	public List<CarEntity> getAllCars() {
		
		return carRepo.findAll();
	}

	@Override
	public List<CarEntity> getCarsByModel(String model) {
	
		return carRepo.findByModelOrderByEngine(model);
	}

	@Override
	public List<CarEntity> getCarsByEngineRange(double from, double to) {
		
		return carRepo.findByEngineBetween(from, to);
	}

	@Override
	public List<CarEntity> getCarsByProductionDate(LocalDate from, LocalDate to) {
		
		return carRepo.findByProductionDateBetween(from, to);
	}

	@Override
	public List<CarEntity> getCarsByByQuery() {
	
		return carRepo.findByQuery();
	}

	@Override
	public List<CarEntity> findEngineByQuery(double from, double to) {
		
		return carRepo.findEngineByQuery(from, to);
	}

	@Override
	public List<CarEntity> findIdByCar(String model, LocalDate productionDate, double engine, boolean ac) {
		
		List<CarEntity> ce= carRepo.findByModelAndProductionDateAndEngineAndAc(model, productionDate, engine, ac);
		
		return ce;
	}
}
