package application.service;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import dto.CarDto;
import dto.PersonDto;

public interface ICarPerson {
		boolean addOwner(PersonDto person);
		boolean addCar(CarDto car);
		List<CarDto> getOwnerCars(int id);
		
		public List<PersonDto> getAllOwners();
		
		public List<CarDto> getAllCars();
		public CarDto removeCar(String carid);
		public PersonDto removePerson(int id);
		
}
