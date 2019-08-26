package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
	public int passportTeacher;
	private String firstnameTeacher;
	private String lastnameTeacher;
}
