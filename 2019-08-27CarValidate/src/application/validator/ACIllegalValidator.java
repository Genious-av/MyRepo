package application.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import DTO.Car;
import application.annotation.ACIllegalAnnotation;

public class ACIllegalValidator implements ConstraintValidator<ACIllegalAnnotation, Car>{
		double minEngine;
		int  minYear;
		
		@Override
		public boolean isValid(Car car, ConstraintValidatorContext context) {
			
			return !car.isAC() || (car.getEngine()>=minEngine&& car.getYear()>minYear);
					//car.getEngine()>=minEngine && car.getYear()>minYear && car.isAC()==true;
		}

		@Override
		public void initialize(ACIllegalAnnotation constraintAnnotation) {
			minEngine=constraintAnnotation.minEngine();
			
			minYear=constraintAnnotation.minYear();
		}
		
		
}
