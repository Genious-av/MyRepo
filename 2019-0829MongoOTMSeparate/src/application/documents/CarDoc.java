package application.documents;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;


import dto.CarDto;
import dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data

@Document(collection="car_otm_sep1")
public class CarDoc {
	@Id
	private String vin;
	private String model;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate productionDate;
	private double engine;
	private boolean ac;
	private int ownerID;
	
	public CarDoc(CarDto car) {
		this.vin=car.getVin();
		this.model=car.getModel();
		this.productionDate=car.getProductionDate();
		this.engine=car.getEngine();
		this.ac=car.isAc();
		this.ownerID=car.getOwner().getId();
	}
	
	
	
	
	public CarDoc(String vin, String model, LocalDate productionDate, double engine, boolean ac) {
		super();
		this.vin = vin;
		this.model = model;
		this.productionDate = productionDate;
		this.engine = engine;
		this.ac = ac;
	}




	public  CarDto getCarDTO(PersonDoc person) {
		PersonDto personDto=(person==null)? null: person.getPersonDto();
		return  new CarDto(vin,model,productionDate,engine,ac, personDto);
	}
	
}
