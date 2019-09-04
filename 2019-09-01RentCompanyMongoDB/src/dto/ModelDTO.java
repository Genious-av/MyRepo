package dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModelDTO {
		String name;
		double rentPrice;
		double tankVolume;
		boolean removed;
}
