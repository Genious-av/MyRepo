package application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.documents.PersonDoc;

public interface PersonMongoRepo extends MongoRepository<PersonDoc, Integer>{

}
