package application.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import DTO.Car;
import application.annotation.ACIllegalAnnotation;
import application.annotation.YearAnnotation;

public class YearIllegalValidator implements ConstraintValidator<YearAnnotation, Car>{
		
		
		
		@Override
		public boolean isValid(Car car, ConstraintValidatorContext context) {
			LocalDate ld=LocalDate.ofYearDay(car.getYear(), 1);
//			Integer dateN=car.getYear();
//			
//			DateTimeFormatter dtf= DateTimeFormatter.ofPattern("YYYY");
//			ld=LocalDate.parse(dateN.toString(),dtf);
			
			return ld.isBefore(LocalDate.now());
				
		}

		
		
		
}
