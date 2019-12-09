package telran.logs.processing.entities;


import org.springframework.data.annotation.Id;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MethodCount {
	@Id
String methodName;
long count;
}
