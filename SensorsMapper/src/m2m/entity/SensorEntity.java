package m2m.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SensorEntity {

    private int sensorId;
    private double[] sensorValue;
    private long timestamp;
    private String sensorType;

}
