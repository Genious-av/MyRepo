package application.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import dto.DriverDTO;
import dto.ModelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

@Document(collection="driver")
public class DriverDoc {

			
	@Id
	private int tz;
	private String name;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate birthDate;
	
	public DriverDoc(DriverDTO driverDTO) {
		this.tz=driverDTO.getTz();
		this.name=driverDTO.getName();
		this.birthDate=driverDTO.getBirthDate();
	}
	
	public DriverDTO getDriverDTO(DriverDoc driverDoc) {
		return new DriverDTO(driverDoc.getTz(), driverDoc.getName(), driverDoc.getBirthDate());
	}
}
