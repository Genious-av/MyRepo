package telran.m2m.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;

import telran.m2m.dto.Sensor;
import telran.m2m.repo.AVGRepo;

@org.springframework.stereotype.Service
public class Service implements IService {
	int msInSec=1000;
	
	@Autowired
	AVGRepo avgRepo;
	
	@Override
	public List<Sensor> getValuesForPeriod(int id, LocalDateTime from, LocalDateTime to) {
		
	return avgRepo.findByIdAndTimestampBetween(id, from.toEpochSecond(ZoneOffset.UTC)*msInSec, to.toEpochSecond(ZoneOffset.UTC)*msInSec);
		
	}

	@Override
	public List<Sensor> getMinMaxValuesForPeriod(int id, LocalDateTime from, LocalDateTime to, int minValue, int maxValue) {
		
		return avgRepo.findByIDAndQuery(id, minValue, maxValue, from.toEpochSecond(ZoneOffset.UTC)*msInSec, to.toEpochSecond(ZoneOffset.UTC)*msInSec);
	}

	@Override
	public List<Sensor> getValuesGreaterThen(int id, int minValue) {
		return avgRepo.findByIDAndQueryGT(id, minValue);
	}

}
