package m2m.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="sensors")
public class SensorDoc {
	@Indexed
public int sensorId;
public int avgValue;
@Indexed
public long timestamp;
public int getSensorId() {
	return sensorId;
}
public int getAvgValue() {
	return avgValue;
}
public long getTimestamp() {
	return timestamp;
}

}
