package application.entity;

import java.time.LocalDateTime;
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
	int id;
	
	String nameOfDish;
	String typeOfDish;
	Float timeofDish;
	String ingridient;
	Float volumeofIngridients;
	String typeOfMesure;
	String commentsToCook;
	//String author;
	LocalDateTime dateOfReceipt;
	
	public ReceiptEntity(String nameOfDish, String typeOfDish, Float timeofDish, String ingridient,
			Float volumeofIngridients, String typeOfMesure, String commentsToCook) {
		super();
		this.nameOfDish = nameOfDish;
		this.typeOfDish = typeOfDish;
		this.timeofDish = timeofDish;
		this.ingridient = ingridient;
		this.volumeofIngridients = volumeofIngridients;
		this.typeOfMesure = typeOfMesure;
		this.commentsToCook = commentsToCook;
		this.dateOfReceipt = LocalDateTime.now();
	}
	
	public ReceiptDTO receiptToDto() {
		return new ReceiptDTO(this.id, this.getNameOfDish(),this.getTypeOfDish(), this.getTimeofDish(), this.getIngridient(),this.getVolumeofIngridients(),this.getTypeOfMesure(),this.getCommentsToCook());
	}
	
	
	
}


