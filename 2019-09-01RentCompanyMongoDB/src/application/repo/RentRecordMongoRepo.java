package application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.entity.CarDoc;
import application.entity.RentRecordDoc;

public interface RentRecordMongoRepo extends MongoRepository<RentRecordDoc, Integer>{

}
