package application.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import application.annotation.AgeAnnotation;
import application.annotation.IllegalMarriage;
import application.entities.Person;

public class AgeValidator implements ConstraintValidator<AgeAnnotation, Person>{

	private int minAge;
	private int maxAge;
	
	
	@Override
	public boolean isValid(Person person, ConstraintValidatorContext context) {
		
		return person.getAge()>=maxAge || person.getAge()<=minAge;
	}


	@Override
	public void initialize(AgeAnnotation constraintAnnotation) {
		minAge=constraintAnnotation.minAge();
		maxAge=constraintAnnotation.maxAge();
	}
	
	
//	@Override
//	public void initialize(IllegalMarriage annotation) {
//		this.minAge=annotation.minAge();
//		this.maxAge=annotation.maxAge();
//	}

}
