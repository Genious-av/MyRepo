package dto;

import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonEntity<T> {
	String json;
	TypeReference<T> type;
}
