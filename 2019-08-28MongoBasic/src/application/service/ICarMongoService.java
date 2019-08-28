package application.service;

import java.util.List;

import application.entities.CarDoc;

public interface ICarMongoService {
	public boolean addCar(CarDoc car);
	public int addCars(List<CarDoc> cars) ;
}