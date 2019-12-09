package telran.logs.processing.entities;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("log")
public class LogDoc {
	LocalDateTime eventDate;
	String className;
	String methodName;
	String result;
	String exception;
	long responseTime;
}

