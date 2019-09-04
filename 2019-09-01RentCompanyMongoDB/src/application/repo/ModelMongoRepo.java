package application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.entity.CarDoc;
import application.entity.ModelDoc;

public interface ModelMongoRepo extends MongoRepository<ModelDoc, String>{

}
