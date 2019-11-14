package telran.aop.repo;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.aop.document.ResDoc;

public interface ResRepo extends MongoRepository<ResDoc, LocalDateTime>{

	
	
	
};
