package dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data


public class DriverDTO {
	int tz;
	String name;
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate birthDate;
}
