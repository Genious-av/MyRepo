package telran.m2m.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import telran.m2m.dto.Sensor;

public interface IService {
	List<Sensor> getValuesForPeriod(int id, LocalDateTime from, LocalDateTime to);
	List<Sensor> getMinMaxValuesForPeriod(int id, LocalDateTime from, LocalDateTime to, int minValue, int maxValue);
	List<Sensor> getValuesGreaterThen(int id, int minValue);
	
}
