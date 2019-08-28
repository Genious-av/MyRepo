package application.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

import javax.validation.Constraint;
import javax.validation.Payload;

import application.validator.ACIllegalValidator;
import application.validator.YearIllegalValidator;


@Constraint(validatedBy=YearIllegalValidator.class)
@Retention(RetentionPolicy.RUNTIME) // used while a programm run
@Target(ElementType.TYPE) // this annotation is used to classtype
public @interface YearAnnotation {
	
	
	
	String message() default "This year still not start";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
