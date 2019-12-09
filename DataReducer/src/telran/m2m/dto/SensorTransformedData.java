package telran.m2m.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorTransformedData {
	 	private int sensorId;
	    private double[] sensorValue;
	    private long timestamp;
	    private String sensorType;
	
}
