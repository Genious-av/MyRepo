package dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {
	private String firstName;
	private String lastName;
	private Address address;
	private Contact[] contacts;
	
	@JsonFormat(pattern="yyyy-MMMM-dd") // format of date in JSON
	private LocalDate birthDate;
	
}
