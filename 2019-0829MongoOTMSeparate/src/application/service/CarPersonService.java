package application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.documents.CarDoc;
import application.documents.PersonDoc;
import application.repo.CarMongoRepo;
import application.repo.PersonMongoRepo;
import dto.CarDto;
import dto.PersonDto;

@Service
public class CarPersonService implements ICarPerson, BeanFactoryAware{
	
	static {
		
		System.out.println("START STATIC");
	}
	@Autowired //automatically creates class by interface
	CarMongoRepo carRepo;
	
	@Autowired
	PersonMongoRepo personRepo;
	

	
	@PostConstruct
	private void postConstruct() {
		System.out.println("POSTCONSTRUCT__@@@@@@@@@@@@@@@");
	}
	
	@PreDestroy
	private void preDectroy() {
		System.out.println("PREDESTROY@@@@@@@@@@@@@@@@@");
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory ) throws BeansException{
		System.out.println("@@@@@@@@@@@@@@@2BEANFACTORY");
	}
	
	
	@Override
	public boolean addOwner(PersonDto person) {
		if(personRepo.existsById(person.getId())) return false;
		personRepo.save(new PersonDoc(person));
		return true; 
	}

	@Override
	public boolean addCar(CarDto car) {
	if(carRepo.existsById(car.getVin())) return false;
	PersonDoc person=personRepo.findById(car.getOwner().getId()).orElse(null);
	if(person==null) person = personRepo.save(new PersonDoc(car.getOwner()));
	person.getCarIDs().add(car.getVin());
	personRepo.save(person);
	carRepo.save(new CarDoc(car));
		return true;
	}

	@Override
	public List<CarDto> getOwnerCars(int id) {
		PersonDoc person = personRepo.findById(id).orElse(null);
		System.out.println("PersonXXXXX: "+person.toString());
		return (person==null)? new ArrayList<CarDto>()  :
														person.getCarIDs().stream()
														.map(idCar->carRepo.findById(idCar).get().getCarDTO(person))
														.collect(Collectors.toList());
		
	}

	@Override
	public List<PersonDto> getAllOwners() {
		
		return personRepo.findAll().stream()
									.map(car->car.getPersonDto())
									.collect(Collectors.toList());
	}

	@Override
	public List<CarDto> getAllCars() {
		
		return carRepo.findAll().stream()
							.map(car->car.getCarDTO(personRepo.findById(car.getOwnerID()).get()))
							.collect(Collectors.toList());
	}
	
	@Override
	public CarDto removeCar(String carid) {
		if(!carRepo.existsById(carid)) return null;
		CarDoc car=carRepo.findById(carid).get();
		PersonDoc person=personRepo.findById(car.getOwnerID()).orElse(null);
		if(person!=null) {
			person.getCarIDs().remove(carid);
			personRepo.save(person);
		}
		
		carRepo.deleteById(carid);
		return car.getCarDTO(person);
	}
	@Override
	public PersonDto removePerson(int id) {
		if(!personRepo.existsById(id)) return null;
		PersonDoc person=personRepo.findById(id).get();
		if (person.isEmpty()) {
			personRepo.deleteById(id);
			return person.getPersonDto();
		}
		return null;
	}

}
