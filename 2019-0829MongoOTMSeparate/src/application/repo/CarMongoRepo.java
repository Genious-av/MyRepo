package application.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.documents.CarDoc;

public interface CarMongoRepo extends MongoRepository<CarDoc, String> {
		List<CarDoc> findByOwnerID(int id);
}
