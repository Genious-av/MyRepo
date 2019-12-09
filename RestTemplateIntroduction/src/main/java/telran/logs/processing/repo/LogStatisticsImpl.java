package telran.logs.processing.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import telran.logs.processing.entities.MethodCount;
@Repository
public class LogStatisticsImpl implements LogStatistics {
	@Autowired
MongoTemplate mongoTemplate;
	@Override
	public List<MethodCount> mostTimeConsumingMethods(int nMethods) {
		GroupOperation groupByMethod = Aggregation.group("methodName")
				.sum("responseTime").as("count");
		SortOperation sortByResponseTime =
				Aggregation.sort(Sort.by(Direction.DESC, "count"));
		LimitOperation limit = Aggregation.limit(nMethods);
		Aggregation pipe =
				Aggregation.newAggregation(groupByMethod, sortByResponseTime, limit);
		AggregationResults<MethodCount> results =
				mongoTemplate.aggregate(pipe, "log", MethodCount.class);
		
		return results.getMappedResults();
	}

	@Override
	public List<MethodCount> methodsCountsNotResponse(String responseCode) {
		MatchOperation match =
				Aggregation.match(new Criteria("result").ne(responseCode)
						.and("exception").exists(false));
		GroupOperation groupByMethod = Aggregation.group("methodName")
				.count().as("count");
		Aggregation pipe = Aggregation.newAggregation(match,groupByMethod);
		AggregationResults<MethodCount> results =
				mongoTemplate.aggregate(pipe, "log", MethodCount.class);
		
		return results.getMappedResults();
	}

}
