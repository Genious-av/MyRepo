package dto;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class Person {
	
	@Size(min=5,max=10,message="Address: Name is too short or too long")
	private String name;
	@Valid
	private Address address;
}
