package application.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

import javax.validation.Constraint;
import javax.validation.Payload;

import application.validator.ACIllegalValidator;
import application.validator.MinMaxDoubleValidator;


@Constraint(validatedBy=MinMaxDoubleValidator.class)
@Retention(RetentionPolicy.RUNTIME) // used while a programm run
@Target(ElementType.TYPE) // this annotation is used to classtype
public @interface MInMaxDoubleAnnotation {
	
	double minEngine();
	double maxEngine();
	
	String message() default "Error in engine volume";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
