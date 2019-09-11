package application.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

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
	@Digits(fraction = 0, integer = 10)
	private int tz;
	@Size(min=3,max=5,message="Incorrect name")
	private String name;
	@JsonFormat(pattern="yyyy-MM-dd")
	@PastOrPresent
	private LocalDate birthDate;
	
	Set<RentRecordDoc> rentRecords;
	
	public DriverDoc(DriverDTO driverDTO) {
		this.tz=driverDTO.getTz();
		this.name=driverDTO.getName();
		this.birthDate=driverDTO.getBirthDate();
	}
	
	public DriverDTO getDriverDTO(DriverDoc driverDoc) {
		return new DriverDTO(driverDoc.getTz(), driverDoc.getName(), driverDoc.getBirthDate());
	}
}
