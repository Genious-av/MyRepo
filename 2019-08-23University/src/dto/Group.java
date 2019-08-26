package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
	private String nameGroup;
	private String courseName;
	private int passportTeacher;
	private Teacher teacher;
}
