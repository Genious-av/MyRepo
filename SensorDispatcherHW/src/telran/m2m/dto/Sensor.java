package telran.m2m.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {
	public int seqNumber;
	public int id; // id of sender
	public int value;
	public long timestamp;
	
}
