package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.CarDoc;
import application.entity.DriverDoc;
import application.entity.ModelDoc;
import application.exceptions.DuplicateDataException;
import application.repo.CarMongoRepo;
import application.repo.DriverMongoRepo;
import application.repo.ModelMongoRepo;
import application.repo.RentRecordMongoRepo;
import dto.CarDTO;
import dto.DriverDTO;
import dto.ModelDTO;
import dto.RentRecordDTO;
@Service
public class CarRentService implements ICarRentService{
	@Autowired
	CarMongoRepo carRepo;
	@Autowired
	DriverMongoRepo driverRepo;
	
	@Autowired
	ModelMongoRepo modelRepo;
	
	@Autowired
	RentRecordMongoRepo rrRepo;
	
	
	@Override
	public boolean addCar(CarDTO carDto) {
		
		if(carRepo.existsById(carDto.getVIN())) throw new DuplicateDataException("Car with VIN: "+carDto.getVIN()+" exists!");
		if(!modelRepo.existsById(carDto.getModel().getName())) addModel(carDto.getModel());
		return (carRepo.save(new CarDoc(carDto))) != null?true:false;
	}

	@Override
	public boolean addModel(ModelDTO modelDTO) {
		if(modelRepo.existsById(modelDTO.getName()))  throw new DuplicateDataException("Model with name: "+modelDTO.getName()+" exists!");
		
		return modelRepo.save(new ModelDoc(modelDTO)) != null?true:false;
	}

	@Override
	public boolean addDriver(DriverDTO driverDTO) {
		if(driverRepo.existsById(driverDTO.getTz()))  throw new DuplicateDataException("Model with tz: "+driverDTO.getTz()+" exists!");
		return driverRepo.save(new DriverDoc(driverDTO)) != null?true:false;
	}

	@Override
	public boolean rentACar(String carID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnCar(String carID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeModel(String modelID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeDriver(int driverID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeDruver(int driverID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CarDTO> getAllCarsByDriver(int driverID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentRecordDTO> getAllrecordsByDriver(int driverID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDTO> getAllNotreturnedCarsByDriver(int driverID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelDTO> getAllModelsByDriver(int driverID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDTO> getAllFreeCars() {
		// TODO Auto-generated method stub
		return null;
	}

}
