package telran.m2m.dto;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection="dbAVG")
public class Sensor {
	@Id
    public ObjectId _id;
	public int id; // id of sender
	public int value;
	public long timestamp;
	
}
