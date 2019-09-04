package dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PersonDto {
	
	private int id;
	private String firstName;
	private String lastName;
	
	
	
}
