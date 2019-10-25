package dto;

import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReceiptDTO {
	int id;
	String nameOfDish;
	String typeOfDish;
	Float timeofDish;
	String ingridient;
	Float volumeofIngridients;
	String typeOfMesure;
	String commentsToCook;
	
}
