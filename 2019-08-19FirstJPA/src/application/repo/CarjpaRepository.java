package application.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.entities.CarEntity;

public interface CarjpaRepository extends JpaRepository<CarEntity, Integer> { //CarEntity - class with Entity, Integer - type of key in DB
	//transform name of function to SQL request
	List<CarEntity> findByModel(String model);  //findBY - special word - shows that this method is for autogenerated request, name of column in Model
	List<CarEntity> findByEngineBetween(double from, double to);
	List<CarEntity> findByModelOrderByEngine (String model); 
	List<CarEntity> findByProductionDateBetween(LocalDate from,LocalDate to);
	List<CarEntity> findByModelAndProductionDateAndEngineAndAc(String model,LocalDate productionDate, double engine, boolean ac);
	
	//poosible to use custom requests
	@Query(value="SELECT*FROM car WHERE id BETWEEN 10 AND 20",nativeQuery=true) //nativeQuery=true shows that query is in SQL format, otherwize used JPQUERY
	List<CarEntity> findByQuery();
	
	@Query(value="SELECT*FROM car WHERE engine BETWEEN ?1 AND ?2",nativeQuery=true) //nativeQuery=true shows that query is in SQL format, otherwize used JPQUERY
	List<CarEntity> findEngineByQuery(double from, double to);
	
}
