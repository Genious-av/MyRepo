package application.service;

import java.time.LocalDate;
import java.util.List;

import application.entities.CarDoc;

public interface ICarMongoService {
	public boolean addCar(CarDoc car);
	public int addCars(List<CarDoc> cars) ;
	public List<CarDoc> findCarsByModel(String model);
	public List<CarDoc> findCarsByEngineRange(double engineFrom, double engineTo);
	public List<CarDoc> findCarsByQuery(double engineFrom, double engineTo);
	public List<CarDoc> findCarsByDate(LocalDate dateFrom, LocalDate dateTo);
	
	
	
	
}