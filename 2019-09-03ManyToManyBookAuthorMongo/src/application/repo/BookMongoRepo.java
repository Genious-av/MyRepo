package application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.documents.BookDoc;

public interface BookMongoRepo extends MongoRepository<BookDoc, Long> {

}
