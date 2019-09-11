package application.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import application.entity.CarDoc;
import application.entity.DriverDoc;
import application.entity.ModelDoc;
import application.entity.RentRecordDoc;
import application.exceptions.DeleteDataException;
import application.exceptions.DuplicateDataException;
import application.exceptions.NoDataFoundException;
import application.exceptions.TooYoungException;
import application.exceptions.WrongDataException;
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
	private static final int MIN_AGE = 18;

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
		if(!modelRepo.existsById(carDto.getModelName())) throw new NoDataFoundException("Model with name: "+carDto.getModelName()+" doesn`t exists");
		ModelDoc model=modelRepo.findById(carDto.getModelName()).orElse(null);
		if(model.getCarIds()==null) model.setCarIds(new HashSet<String>());
		model.getCarIds().add(carDto.getVIN());
		modelRepo.save(model);
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
		int age=Period.between(driverDTO.getBirthDate(), LocalDate.now()).getYears();
		System.out.println("age="+age);
		if(age<MIN_AGE) throw new TooYoungException("The driver is too young");
		return driverRepo.save(new DriverDoc(driverDTO)) != null?true:false;
	}

	@Override
	public String rentACar(RentRecordDTO rrDTO ) {
		if(!carRepo.existsById(rrDTO.getCarVin())) throw new NoDataFoundException("Car with VIN: "+rrDTO.getCarVin()+" doesn`t exists");
		if(carRepo.findById(rrDTO.getCarVin()).orElse(null).isInUse()) throw  new WrongDataException("Car with VIN: "+rrDTO.getCarVin()+" is in use");
		if(carRepo.findById(rrDTO.getCarVin()).orElse(null).isRemoved()) throw new WrongDataException("Car with VIN: "+rrDTO.getCarVin()+" is removed");
		DriverDoc driver = new DriverDoc();
		driver=driverRepo.findById(rrDTO.getDiverTZ()).orElse(null);
		if(driver.getRentRecords()==null) driver.setRentRecords(new HashSet<RentRecordDoc>());
		RentRecordDoc rrDoc=new RentRecordDoc(rrDTO);
		double totalPrice=rrDoc.getRentDays()*getRentPrice(rrDoc.getRentRecordDTO());
		rrDoc.setTotalPrice(totalPrice);
		
		driver.getRentRecords().add(rrDoc);
		driverRepo.save(driver);
		CarDoc carDoc=carRepo.findById(rrDTO.getCarVin()).orElse(null);
		carDoc.setInUse(true);
		carRepo.save(carDoc);
		rrRepo.save(rrDoc);
		return "Car rented sucessfully! /n prognosed total price: "+totalPrice+" shekel";
		
	}

	@Override
	public String returnCar(String carID, LocalDate returnDate, int tankPercent) {
		if(rrRepo.findByCarVin(carID).size()==0) throw new NoDataFoundException("Car with VIN: "+carID+" doesn`t exists");
		RentRecordDoc rrDoc=rrRepo.findByCarVin(carID).get(0);
		
		if(carRepo.findById(rrDoc.getCarVin()).orElse(null).isInUse()==false) throw  new WrongDataException("Car with VIN: "+rrDoc.getCarVin()+" is not rented");
		if(carRepo.findById(rrDoc.getCarVin()).orElse(null).isRemoved()) throw new WrongDataException("Car with VIN: "+rrDoc.getCarVin()+" is removed");
		//calculate total price
		rrDoc.setReturnDate(returnDate);
		rrDoc.setRentDays(Period.between(returnDate,rrDoc.getRentDate()).getDays());
		double totalPrice=rrDoc.getRentDays()*getRentPrice(rrDoc.getRentRecordDTO());
		rrDoc.setTotalPrice(totalPrice);
		int deltaPerBenzin=0;
		if(rrDoc.getTankPercent()>tankPercent) deltaPerBenzin=rrDoc.getTankPercent()-tankPercent;
		//set in useFalse
		CarDoc carDoc=carRepo.findById(rrDoc.getCarVin()).orElse(null);
		carDoc.setInUse(false);
		carRepo.save(carDoc);
		rrRepo.save(rrDoc);
		if(deltaPerBenzin!=0) return "Car returned sucessfully! Total price: "+totalPrice+" shekel  You should pay for "+ deltaPerBenzin*modelRepo.findById(carDoc.getModelName()).orElse(null).getTankVolume();
		return "Car returned sucessfully! Total price: "+totalPrice+" shekel";
	}

	@Override
	public boolean removeModel(String modelID) {
		if(!modelRepo.existsById(modelID)) throw new NoDataFoundException("Model named: "+modelID+" doesn`t exists");
		if(modelRepo.findById(modelID).orElse(null).getCarIds().size()!=0) throw new DeleteDataException("Model "+modelID+" has actual Cars");
		ModelDoc model = modelRepo.findById(modelID).orElse(null);
		model.setRemoved(true);
		return modelRepo.save(model)!= null?true:false;
	}

	@Override
	public boolean removeDriver(int driverID) {
		if(!driverRepo.existsById(driverID)) throw new NoDataFoundException("Driver with id : "+driverID+" doesn`t exists");
		for(RentRecordDoc rrDoc:rrRepo.findByDiverTZ(driverID)) {
			if (carRepo.findById(rrDoc.getCarVin()).orElse(null).isInUse()) throw new DeleteDataException("Driver "+driverID+" has actual rents");
		}
		driverRepo.deleteById(driverID)	;
		return true;
	}

	

	@Override
	public List<CarDTO> getAllCarsByDriver(int driverID) {
		List<CarDTO> result=new ArrayList<CarDTO>();
		for(RentRecordDoc rrDoc: driverRepo.findById(driverID).orElse(null).getRentRecords()){
			result.add(carRepo.findById(rrDoc.getCarVin()).orElse(null).getCarDTO());
		}
		return result;
	}

	@Override
	public List<RentRecordDTO> getAllrecordsByDriver(int driverID) {
		
		return driverRepo.findById(driverID).orElse(null).getRentRecords().stream()
				.map(rr->rr.getRentRecordDTO())
				.collect(Collectors.toList());
	}
 
	@Override
	public List<CarDTO> getAllNotreturnedCarsByDriver(int driverID) {
		return driverRepo.findById(driverID).orElse(null).getRentRecords().stream()
		.map(rr->rr.getRentRecordDTO())
		.filter(rr->rr.getReturnDate().equals(null))
		.map(rr->carRepo.findById(rr.getCarVin()).orElse(null).getCarDTO())
		.collect(Collectors.toList());
	}

	@Override
	public List<ModelDTO> getAllModelsByDriver(int driverID) {
		
		return rrRepo.findByDiverTZ(driverID).stream()
				.map(rr->carRepo.findById(rr.getCarVin()).orElse(null).getModelName())
				.map(modelName->modelRepo.findById(modelName).orElse(null).getModelDTO())
				.collect(Collectors.toList());
	}

	@Override
	public List<CarDTO> getAllFreeCars() {
		
		return carRepo.findAll().stream()
								.filter(car->car.isInUse()==false)
								.map(car->car.getCarDTO())
								.collect(Collectors.toList());
	}
	
	
	public double getRentPrice(RentRecordDTO rrDTO) {
		String modelName=carRepo.findById(rrDTO.getCarVin()).orElse(null).getModelName();
		return modelRepo.findById(modelName).orElse(null).getRentPrice();
	}
	
	
}
