package application.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import DTO.Car;
import DTO.Models;
import application.annotation.ACIllegalAnnotation;
import application.annotation.ModelAnnotation;

public class ModelValidator implements ConstraintValidator<ModelAnnotation, Car>{
		Models model;
		
		
		@Override
		public boolean isValid(Car car, ConstraintValidatorContext context) {
			ArrayList<String> alM=new ArrayList<>();
			for(Models m: model.values()) alM.add(m.toString());
			return alM.contains(car.getModel());
		}

		@Override
		public void initialize(ModelAnnotation constraintAnnotation) {
			
		}
		
		
}
