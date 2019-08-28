package application.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

import javax.validation.Constraint;
import javax.validation.Payload;

import DTO.Models;
import application.validator.ACIllegalValidator;
import application.validator.ModelValidator;


@Constraint(validatedBy=ModelValidator.class)
@Retention(RetentionPolicy.RUNTIME) // used while a programm run
@Target(ElementType.TYPE) // this annotation is used to classtype
public @interface ModelAnnotation {
	
	//Models model();
	
	
	String message() default "No such car Model";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
