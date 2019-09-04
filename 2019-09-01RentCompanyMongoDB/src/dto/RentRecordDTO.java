package dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentRecordDTO {
	CarDTO car;
	DriverDTO driver;
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate rentDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate returnDate;
	int rentDays;
	int tankPercent;
	double totalPrice;
	
}
