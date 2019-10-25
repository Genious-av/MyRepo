package application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.documents.AccountDoc;

public interface AccountRepo extends MongoRepository<AccountDoc, String>{

}
