package dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import application.documents.PersonDoc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data



public class CarDto {
	
	
	private String vin;
	private String model;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate productionDate;
	private double engine;
	private boolean ac;
	private PersonDto owner;
}


