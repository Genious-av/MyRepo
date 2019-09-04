package application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.entity.CarDoc;

public interface CarMongoRepo extends MongoRepository<CarDoc, String>{

}
