package application.service;

import java.time.LocalDate;
import java.util.List;

import application.entities.CarEntity;

public interface ICarService { // @Autowired uses interface to creates tables. WILL NOT WORK WITHOUT CREATING INTERFACE
	
		boolean addCar(CarEntity car);
		void addManyCars(List<CarEntity> listCar);
		CarEntity getCarById(int id);
		List<CarEntity> getAllCars();
		
		List<CarEntity> getCarsByModel(String model);
		
		List<CarEntity> getCarsByEngineRange(double from, double to);
		
		List<CarEntity> getCarsByProductionDate(LocalDate from,LocalDate to);
		
		List<CarEntity> getCarsByByQuery();
		List<CarEntity> findEngineByQuery(double from, double to);
		
		List<CarEntity> findIdByCar(String model, LocalDate productionDate, double engine, boolean ac);
}
