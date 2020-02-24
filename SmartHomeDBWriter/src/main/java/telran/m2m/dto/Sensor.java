package telran.m2m.dto;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("smartHomeData")
public class Sensor {

	@Id
	ObjectId _id;
	
	private int sensorId;
    private double[] sensorValue;
    private double[] sensorValuePrev;
    private long timestamp;
    private long timestampPrev;
    private String sensorType;
}
