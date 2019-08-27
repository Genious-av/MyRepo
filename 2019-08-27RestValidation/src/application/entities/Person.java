package application.entities;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import application.annotation.AgeAnnotation;
import application.annotation.IllegalMarriage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
@IllegalMarriage(minAge=18,message="Too young to be married")
@AgeAnnotation(minAge=16, maxAge=65, message="You are looser")
public class Person {
	
	@NotNull
	@Size (min=3, max=6, message="Person: name is too short or too long")
	private String name;
	
	@PositiveOrZero(message="Person: negative age")
	@Max(value=100, message="Person: age is too big")
	private int age;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd") //pattern for type of date
	
	@FutureOrPresent //check date - return error if input date is before future
	@PastOrPresent //check date - return error if input date is after past
	private LocalDate birthDate;
	private double weight;
	private boolean married;

}
