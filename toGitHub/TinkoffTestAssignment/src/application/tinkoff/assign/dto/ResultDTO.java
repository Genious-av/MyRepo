package application.tinkoff.assign.dto;

import java.util.List;

import application.tinkoff.config.CodeResults;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultDTO {
	String code;
	List<String> fileNames;
	String error;
}
