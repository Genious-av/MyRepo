package application.documents;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Document(collection="person_otm_sep")
public class PersonDoc {
	
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private Set<String> carIDs = new HashSet<>();
	
	public PersonDoc (PersonDto person) {
		this.id=person.getId();
		this.firstName=person.getFirstName();
		this.lastName=person.getLastName();
	}
	
	public PersonDto getPersonDto() {
		return new PersonDto(id,firstName, lastName);
	}
	
	public boolean isEmpty() {
		return carIDs.isEmpty();
	}
}
