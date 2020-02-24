package telran.m2m.dto;

import java.util.Arrays;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SensorTransformedData {
	 	private int sensorId;
	    private double[] sensorValue;
	    private long timestamp;
	    private String sensorType;
		
	    
	    
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + sensorId;
			result = prime * result + ((sensorType == null) ? 0 : sensorType.hashCode());
			result = prime * result + Arrays.hashCode(sensorValue);
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SensorTransformedData other = (SensorTransformedData) obj;
			if (sensorId != other.sensorId)
				return false;
			if (sensorType == null) {
				if (other.sensorType != null)
					return false;
			} else if (!sensorType.equals(other.sensorType))
				return false;
			if (!Arrays.equals(sensorValue, other.sensorValue))
				return false;
			return true;
		}
	
}
