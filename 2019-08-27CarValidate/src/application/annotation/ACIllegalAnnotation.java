package application.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

import javax.validation.Constraint;
import javax.validation.Payload;

import application.validator.ACIllegalValidator;


@Constraint(validatedBy=ACIllegalValidator.class)
@Retention(RetentionPolicy.RUNTIME) // used while a programm run
@Target(ElementType.TYPE) // this annotation is used to classtype
public @interface ACIllegalAnnotation {
	
	double minEngine();
	int minYear();
	
	String message() default "No AC in car with such parameters";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
