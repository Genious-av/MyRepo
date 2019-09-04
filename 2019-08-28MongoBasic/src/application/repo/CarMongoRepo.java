package application.repo;



import java.time.LocalDate;
import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import application.entities.CarDoc;

public interface CarMongoRepo extends MongoRepository<CarDoc, String>{ //ObjectId - type of ID in MONGO
	List<CarDoc> findByModel(String model);
	List<CarDoc> findByEngineBetween(double engineFrom, double engineTo);
	
	@Query("{'engine':{$gte:?0, $lte:?1}}")
	List<CarDoc> findByQuery(double engineFrom, double engineTo);
	
	List<CarDoc> findByProductionDateBetween(LocalDate dateFrom, LocalDate dateTo);
	
}
