package application.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Embeddable
@AllArgsConstructor
public class EmployeeId implements Serializable {
	private String firstName;
	private String lastName;
	
	
	
}
