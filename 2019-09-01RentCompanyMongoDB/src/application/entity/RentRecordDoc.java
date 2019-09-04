package application.entity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import application.repo.CarMongoRepo;
import application.repo.DriverMongoRepo;
import dto.CarDTO;
import dto.DriverDTO;
import dto.RentRecordDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data


@Document(collection="rentrecord")
public class RentRecordDoc {
	
	@Autowired
	CarMongoRepo carRepo;
	
	@Autowired
	DriverMongoRepo driverRepo;
	
	CarDoc carDoc=new CarDoc();
	DriverDoc driverDoc=new DriverDoc();
	
	
	@Id
	private int id;
	private String CarVin;
	private int driverTZ;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate rentDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate returnDate;
	private int rentDays;
	private int tankPercent;
	private double totalPrice;
	
	//constructor for renting car
	public RentRecordDoc(int id, String carVin, int driverTZ, LocalDate rentDate, int rentDays, int tankPercent,
			double totalPrice) {
		super();
		this.id = id;
		this.CarVin = carVin;
		this.driverTZ = driverTZ;
		this.rentDate = rentDate;
		this.rentDays = rentDays;
		this.tankPercent = tankPercent;
		this.totalPrice = totalPrice;
	}
	
	
	//constructor for returning car
	
	public RentRecordDoc(int id, LocalDate returnDate, int tankPercent) {
		super();
		this.id = id;
		this.returnDate = returnDate;
		this.tankPercent = tankPercent;
	}
	
	public RentRecordDoc (RentRecordDTO rentRecordDTO) {
		this.CarVin=rentRecordDTO.getCar().getVIN();
		this.driverTZ=rentRecordDTO.getDriver().getTz();
		this.rentDate=rentRecordDTO.getRentDate();
		this.rentDays=rentRecordDTO.getRentDays();
		this.tankPercent=rentRecordDTO.getTankPercent();
		this.totalPrice=rentRecordDTO.getTotalPrice();
	}
	
	public RentRecordDTO getRentRecordDTO(RentRecordDoc rentRecordDoc) {
		return new RentRecordDTO(carDoc.getCarDTO(carRepo.findById(CarVin).orElseGet(null)), driverDoc.getDriverDTO(driverRepo.findById(rentRecordDoc.getDriverTZ()).orElse(null)),
				rentRecordDoc.getRentDate(), rentRecordDoc.getReturnDate(), rentDays, tankPercent, totalPrice);
	}

	
	
}


