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

public class Employee extends Person {
	private double salary;

	public Employee(String name, LocalDate bithDate, double salary) {
		super(name, bithDate);
		this.salary = salary;
	}

}
