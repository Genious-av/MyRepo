package application.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dto.ModelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data


@Document(collection="model")
public class ModelDoc {
	@Id
	private String name;
	private double rentPrice;
	private double tankVolume;
	private boolean removed;
	
	
	public ModelDoc(ModelDTO modelDTO) {
		this.name=modelDTO.getName();
		this.rentPrice=modelDTO.getRentPrice();
		this.tankVolume=modelDTO.getTankVolume();
		this.removed=modelDTO.isRemoved();
	}
	
	public ModelDTO getModelDTO() {
		return new ModelDTO(this.getName(), this.getRentPrice(), this.getTankVolume(), this.isRemoved());
	}
	
	
}
