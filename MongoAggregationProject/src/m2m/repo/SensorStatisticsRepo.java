package m2m.repo;

import m2m.domain.SensorStatistics;

public interface SensorStatisticsRepo {
SensorStatistics getSensorStatistics(int sensorId,long timeFrom, long timeTo);
}
