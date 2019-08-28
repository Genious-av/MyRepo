package DTO;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import application.annotation.ACIllegalAnnotation;
import application.annotation.MInMaxDoubleAnnotation;
import application.annotation.ModelAnnotation;
import application.annotation.YearAnnotation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter

@ACIllegalAnnotation(minEngine=1.5, minYear=1980,message="No AC in car with such parameters")
@MInMaxDoubleAnnotation(minEngine=1.0, maxEngine=3.0, message ="Error in engine volume")
@ModelAnnotation()
@YearAnnotation()
public class Car {
	
		@NotNull
		String model;
		
		
		@NotNull
		@Min(value=1980)
		
		int year;
		
		@NotNull
		double engine;
		
		@NotNull
		boolean AC;
}
