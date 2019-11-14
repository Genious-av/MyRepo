package m2m.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import m2m.domain.SensorDoc;
import m2m.domain.SensorStatistics;
import m2m.repo.SensorRepo;
@Service
public class BackOffice implements IBackOffice {
@Autowired
SensorRepo sensorRepo;
	@Override
	public List<Integer> getIdsBigValues(LocalDateTime from, LocalDateTime to, int sensorValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getIdsSmallValues(LocalDateTime from, LocalDateTime to, int sensorValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalDateTime> getDatesBigValues
	(int sensorId, LocalDateTime from, LocalDateTime to, int sensorValue) {
		List<SensorDoc> sensors = 
				sensorRepo
				.findBySensorIdAndTimestampBetweenAndAvgValueGreaterThan(sensorId,
						getTimeMilli(from),getTimeMilli(to),sensorValue);
		
		return sensors.stream().map(this::getLocalDateTime)
				.collect(Collectors.toList());
	}
	private LocalDateTime getLocalDateTime(SensorDoc sensor) {
		return LocalDateTime.ofEpochSecond
				(sensor.timestamp/1000, 0, ZoneOffset.ofTotalSeconds(0));
	}

	@Override
	public List<LocalDateTime> getDatesSmallValues(int sensorId, LocalDateTime from, LocalDateTime to,
			int sensorValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorStatistics getSensorStatistics(int sensorId, LocalDateTime from, LocalDateTime to) {
		
		return sensorRepo.getSensorStatistics
				(sensorId, getTimeMilli(from), getTimeMilli(to));
	}

	private long getTimeMilli(LocalDateTime ldt) {
		
		return ldt.toEpochSecond
				(ZoneOffset.ofTotalSeconds(0))*1000;
	}
	
	
	

}
