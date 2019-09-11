package application.service;

import java.time.LocalDate;
import java.util.List;

import application.entity.CarDoc;
import application.entity.DriverDoc;
import application.entity.ModelDoc;
import application.entity.RentRecordDoc;
import dto.CarDTO;
import dto.DriverDTO;
import dto.ModelDTO;
import dto.RentRecordDTO;

public interface ICarRentService {
		boolean addCar(CarDTO carDto);
		boolean addModel(ModelDTO modelDTO);
		boolean addDriver(DriverDTO driverDTO);
		public String rentACar(RentRecordDTO rrDTO);
		public String returnCar(String carID, LocalDate returnDate, int tankPercent);
		boolean removeModel(String  modelID);
		boolean removeDriver(int driverID);
		
		List<CarDTO> getAllCarsByDriver(int driverID);
		List<RentRecordDTO> getAllrecordsByDriver(int driverID);
		List<CarDTO> getAllNotreturnedCarsByDriver(int driverID);
		List<ModelDTO> getAllModelsByDriver(int driverID);
		List<CarDTO> getAllFreeCars();
		
		
		//service methods
//		public  ModelDoc getModelFromCarName(String carName);
//		public CarDoc getCarFromCarName(String carVin);
//		public DriverDoc getDriverFromRR(int driverID);
		
		
}
		
