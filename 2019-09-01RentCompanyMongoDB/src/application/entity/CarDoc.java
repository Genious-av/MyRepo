package application.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import application.repo.CarMongoRepo;
import application.repo.ModelMongoRepo;
import application.service.CarRentService;
import dto.CarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

@Document(collection="car")
public class CarDoc {
		
	
	@Id
	@NotNull
	@Size(min=3,max=5,message="Incorrect VIN")
	private String VIN;
	@NotNull
	@Size(min=3,max=10,message="Incorrect Model")
	private String modelName;
	private boolean inUse; 
	private boolean removed;
	Set<RentRecordDoc> rentRecords;
	
	public CarDTO getCarDTO() {
		return new CarDTO(this.VIN, this.modelName, this.inUse, this.removed);
	}
	
	
	public CarDoc(CarDTO carDto) {
		
		
		this.VIN=carDto.getVIN();
		this.modelName=carDto.getModelName();
		this.inUse=carDto.isInUse();
		this.removed=carDto.isRemoved();
		
	}

	


	
	
	
}
