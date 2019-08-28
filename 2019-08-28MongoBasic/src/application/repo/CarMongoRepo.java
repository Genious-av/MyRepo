package application.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import application.entities.CarDoc;

public interface CarMongoRepo extends MongoRepository<CarDoc, String>{ //ObjectId - type of ID in MONGO

}
