package application.entity;

import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dto.IngridientDTO;
import dto.ReceiptDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name="receipts")

public class ReceiptEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int idOfReceipt;
	String title;
	Enum typeOfDish;
	Float timeOfCooking;
	//HashSet<IngridientDTO> ingridients;
	String volumeOfIngridients;
	String cookOrder;
}
