package dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDTO {
		String VIN;
		String modelName;
		boolean inUse;
		boolean removed;
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CarDTO other = (CarDTO) obj;
			if (VIN == null) {
				if (other.VIN != null)
					return false;
			} else if (!VIN.equals(other.VIN))
				return false;
			return true;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((VIN == null) ? 0 : VIN.hashCode());
			return result;
		}
	
		
		
}
