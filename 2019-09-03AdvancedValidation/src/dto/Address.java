package dto;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Valid
public class Address {
	
	@Size(min=5,max=10,message="Address: city is too short or too long")
	private String city;
}
