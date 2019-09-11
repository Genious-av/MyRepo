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
	String carVin;
	int diverTZ;
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate rentDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate returnDate;
	int rentDays;
	int tankPercent;
	double totalPrice;
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentRecordDTO other = (RentRecordDTO) obj;
		if (carVin == null) {
			if (other.carVin != null)
				return false;
		} else if (!carVin.equals(other.carVin))
			return false;
		if (diverTZ != other.diverTZ)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carVin == null) ? 0 : carVin.hashCode());
		result = prime * result + diverTZ;
		return result;
	}
	
	
	
	
}
