package application.entity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import application.repo.CarMongoRepo;
import application.repo.DriverMongoRepo;
import application.repo.ModelMongoRepo;
import application.service.CarRentService;
import dto.CarDTO;
import dto.DriverDTO;
import dto.ModelDTO;
import dto.RentRecordDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data


@Document(collection="rentrecord")
public class RentRecordDoc {
	private static final int MAX_TANK = 100;

	@Id
	String id;
	
	private String carVin;
	private int diverTZ;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate rentDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate returnDate;
	private int rentDays;
	private int tankPercent;
	private double totalPrice;
	
	
	
	public RentRecordDoc(RentRecordDTO rrDTO) {
		this.carVin=rrDTO.getCarVin();
		this.diverTZ=rrDTO.getDiverTZ();
		this.rentDate=rrDTO.getRentDate();
		this.returnDate=rrDTO.getReturnDate();
		this.rentDays=rrDTO.getRentDays();
		this.tankPercent=rrDTO.getTankPercent();
		}
	
	public RentRecordDTO getRentRecordDTO() {
		return new RentRecordDTO(this.carVin, this.diverTZ, this.rentDate, this.returnDate, this.rentDays, this.tankPercent, this.totalPrice);
	}
	


//constructor for renting car
	public RentRecordDoc(int id, String carVin, int diverTZ, LocalDate rentDate, int rentDays, int tankPercent) {
		super();
		this.carVin = carVin;
		this.diverTZ = diverTZ;
		this.rentDate = rentDate;
		this.rentDays = rentDays;
		this.tankPercent = MAX_TANK;
	}
	public RentRecordDoc(String carVin, LocalDate returnDate, int tankPercent) {
		super();
		this.carVin = carVin;
		this.returnDate = returnDate;
		this.tankPercent = tankPercent;
	}
	
	
}


