package application.service;

import java.util.List;

import application.entity.CarDoc;
import dto.CarDTO;
import dto.DriverDTO;
import dto.ModelDTO;
import dto.RentRecordDTO;

public interface ICarRentService {
		boolean addCar(CarDTO carDto);
		boolean addModel(ModelDTO modelDTO);
		boolean addDriver(DriverDTO driverDTO);
		boolean rentACar(String carID);
		boolean returnCar(String carID);
		boolean removeModel(String  modelID);
		boolean removeDriver(int driverID);
		boolean removeDruver(int driverID);
		List<CarDTO> getAllCarsByDriver(int driverID);
		List<RentRecordDTO> getAllrecordsByDriver(int driverID);
		List<CarDTO> getAllNotreturnedCarsByDriver(int driverID);
		List<ModelDTO> getAllModelsByDriver(int driverID);
		List<CarDTO> getAllFreeCars();
		
}
		
