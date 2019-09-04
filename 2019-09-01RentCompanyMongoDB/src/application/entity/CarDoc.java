package application.entity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import application.repo.ModelMongoRepo;
import dto.CarDTO;
import dto.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

@Document(collection="car")
public class CarDoc {
	
	@Autowired
	ModelMongoRepo modelRepo;
	
	
	@Id
	private String VIN;
	private String modelName;
	private boolean inUse;
	private boolean removed;
	
	public CarDoc(CarDTO carDTO) {
		this.VIN=carDTO.getVIN();
		this.modelName=carDTO.getModel().getName();
		this.inUse=carDTO.isInUse();
		this.removed=carDTO.isRemoved();
	}
	
	
	
	public CarDTO getCarDTO(CarDoc carDoc) {
		return new CarDTO(carDoc.getVIN(),modelRepo.findById(carDoc.getModelName()).orElse(null).getModelDTO(), carDoc.isInUse(), carDoc.isRemoved());
	}
}
