package application.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.entity.CarDoc;
import application.entity.RentRecordDoc;

public interface RentRecordMongoRepo extends MongoRepository<RentRecordDoc, Integer>{

	List<RentRecordDoc> findByCarVin(String carVin);
	List<RentRecordDoc> findByDiverTZ(int driverTZ);
	
}
