package m2m.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import m2m.domain.SensorStatistics;
@Repository
public class SensorStatisticsRepoImpl implements SensorStatisticsRepo {
@Autowired
MongoTemplate mongoTemplate;
	@Override
	public SensorStatistics getSensorStatistics(int sensorId, long timeFrom, long timeTo) {
		MatchOperation matchOp = Aggregation.match(
				new Criteria("sensorId").is(sensorId)
				.andOperator(new Criteria("timestamp")
						.gt(timeFrom).lt(timeTo)));
		GroupOperation grOp = Aggregation.group("sensorId")
				.avg("avgValue").as("avgValue").min("avgValue")
				.as("minValue").max("avgValue").as("maxValue");
		Aggregation pipe = Aggregation.newAggregation(matchOp,grOp);
		AggregationResults<SensorStatistics> result = 
				mongoTemplate.aggregate(pipe, "sensors", SensorStatistics.class);
		return result.getUniqueMappedResult();
	}

}
