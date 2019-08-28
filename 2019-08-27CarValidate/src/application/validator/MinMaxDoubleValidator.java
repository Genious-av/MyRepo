package application.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import DTO.Car;
import application.annotation.ACIllegalAnnotation;
import application.annotation.MInMaxDoubleAnnotation;

public class MinMaxDoubleValidator implements ConstraintValidator<MInMaxDoubleAnnotation, Car>{
		double minEngine;
		double  maxEngine;
		
		@Override
		public boolean isValid(Car car, ConstraintValidatorContext context) {
			
			return car.getEngine()>=minEngine && car.getEngine()<=maxEngine;
		}

		@Override
		public void initialize(MInMaxDoubleAnnotation constraintAnnotation) {
			minEngine=constraintAnnotation.minEngine();
			
			maxEngine=constraintAnnotation.maxEngine();
		}
		
		
}
