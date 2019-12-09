package telran.m2m.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import telran.m2m.dto.Sensor;

public interface AVGRepo extends MongoRepository<Sensor, ObjectId>{

}
