package m2m.domain;

import org.springframework.data.annotation.Id;

public class SensorStatistics {
	//@Id
int sensorId;
int minValue;
int maxValue;
int avgValue;
public int getSensorId() {
	return sensorId;
}
public int getMinValue() {
	return minValue;
}
public int getMaxValue() {
	return maxValue;
}
public int getAvgValue() {
	return avgValue;
}

}
