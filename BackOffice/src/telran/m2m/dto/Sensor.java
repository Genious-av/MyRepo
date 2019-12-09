package telran.m2m.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection="dbAVG")
public class Sensor {
	
	public int id; // id of sender
	public int value;
	@Id
	public long timestamp;
	
}
