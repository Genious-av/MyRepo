package telran.m2m.dto;

import java.util.Arrays;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SensorReducedData {
	  private int sensorId;
	    private double[] sensorValue;
	    private double[] sensorValuePrev;
	    private long timestamp;
	    private long timestampPrev;
	    private String sensorType;
	    
	    
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SensorReducedData other = (SensorReducedData) obj;
			if (sensorId != other.sensorId)
				return false;
			if (sensorType == null) {
				if (other.sensorType != null)
					return false;
			} else if (!sensorType.equals(other.sensorType))
				return false;
			if (!Arrays.equals(sensorValue, other.sensorValue))
				return false;
			if (!Arrays.equals(sensorValuePrev, other.sensorValuePrev))
				return false;
			if (timestamp != other.timestamp)
				return false;
			if (timestampPrev != other.timestampPrev)
				return false;
			return true;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + sensorId;
			result = prime * result + ((sensorType == null) ? 0 : sensorType.hashCode());
			result = prime * result + Arrays.hashCode(sensorValue);
			result = prime * result + Arrays.hashCode(sensorValuePrev);
			result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
			result = prime * result + (int) (timestampPrev ^ (timestampPrev >>> 32));
			return result;
		}
	
}
