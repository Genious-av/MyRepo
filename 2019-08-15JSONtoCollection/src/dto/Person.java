package dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonTypeInfo(use=Id.CLASS) //used to show JAKSON that child and employees have parent class Person
public class Person {
	private String name;
	private LocalDate bithDate;
	
}
