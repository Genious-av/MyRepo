package m2m.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import m2m.domain.SensorDoc;

public interface SensorRepo extends SensorStatisticsRepo,
MongoRepository<SensorDoc, Object> {

	List<SensorDoc> findBySensorIdAndTimestampBetweenAndAvgValueGreaterThan(int sensorId, long timeMilli,
			long timeMilli2, int sensorValue);

}
