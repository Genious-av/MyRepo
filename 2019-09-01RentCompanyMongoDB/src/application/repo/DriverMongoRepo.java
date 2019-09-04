package application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.entity.CarDoc;
import application.entity.DriverDoc;

public interface DriverMongoRepo extends MongoRepository<DriverDoc, Integer>{

}
