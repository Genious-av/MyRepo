package dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true) //used to get all fields from the parent class
public class Child extends Person {
 
	private String kinderGarten;

	public Child(String name, LocalDate bithDate, String kinderGarten) {
		super(name, bithDate);
		this.kinderGarten = kinderGarten;
	}
 
	
}
