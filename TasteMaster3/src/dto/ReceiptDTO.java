package dto;

import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReceiptDTO {
	
	String title;
	Enum typeOfDish;
	Float timeOfCooking;
	//HashSet<IngridientDTO> ingridients;
	String volumeOfIngridients;
	String cookOrder;
	
}
