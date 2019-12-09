package telran.m2m.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import telran.m2m.dto.Sensor;

public interface AVGRepo extends MongoRepository<Sensor, Long>{
	
	List<Sensor> findByIdAndTimestampBetween(int id, long from, long to);
	
	@Query("{$and:[{id:?0}, {$and: [{$and: [{'value': {$gte:?1}},{'value':{$lte:?2}}]},{$and: [{'timestamp': {$gte: ?3}},{'timestamp':{$lte: ?4}}]}]}]}")
	List<Sensor> findByIDAndQuery(int id, int min, int max, long from, long to);
	
	
	@Query("{$and:[{id:?0},{'value': {$gte:?1}}]}")
	List<Sensor> findByIDAndQueryGT(int id, int min);
}
