package telran.m2m.dto;

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
	
}
