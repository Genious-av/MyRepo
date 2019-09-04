package application.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Document(collection="car_otm") //annotation shows to SpringBOOT name of collection in Mongo
public class CarDoc {
	
	@Id
	private String vin;
	private String model;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate productionDate;
	private double engine;
	private boolean ac;
	private Person owner;
	
}
