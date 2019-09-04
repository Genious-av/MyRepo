package application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.documents.AuthorDoc;

public interface AuthorMongoRepo extends MongoRepository<AuthorDoc, Integer>{

}
