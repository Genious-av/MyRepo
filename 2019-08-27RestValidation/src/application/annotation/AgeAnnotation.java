package application.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import application.validators.AgeValidator;
import application.validators.IllegalMarriageValidator;
@Constraint(validatedBy=AgeValidator.class)
@Retention(RetentionPolicy.RUNTIME) // used while a programm run
@Target(ElementType.TYPE) // this annotation is used to classtype
public @interface AgeAnnotation {
	int minAge();
	int maxAge();
	String message() default "Too young to be married";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
}
