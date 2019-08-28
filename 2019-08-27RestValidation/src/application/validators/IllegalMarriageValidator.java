package application.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import application.annotation.IllegalMarriage;
import application.entities.Person;

public class IllegalMarriageValidator implements ConstraintValidator<IllegalMarriage, Person>{

	private int minAge;
	
	
	@Override
	public boolean isValid(Person person, ConstraintValidatorContext context) {
		
		return !person.isMarried() || person.getAge()>=minAge;
	}
	@Override
	public void initialize(IllegalMarriage annotation) {
		this.minAge=annotation.minAge();
	}

}
