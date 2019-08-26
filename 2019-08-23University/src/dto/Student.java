package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {
	private int passportStudent;
	private String nameStudent;
	private int age;
	private String nameGroup;
}
