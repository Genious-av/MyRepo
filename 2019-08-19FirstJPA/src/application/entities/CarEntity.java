package application.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dto.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="car")
public class CarEntity {
	//THIS CLASS IS A VIEW OF NEEDED TABLE IN DB, WHICH SHOWS ONLY FIELDS WE NEED
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //startegy - field of annotation must have, generationtype.identity - DB generates id authomatically
	private int id;
	
	private String model;
	
	private LocalDate productionDate;
	private double engine;
	private boolean ac;
	
	public CarEntity(String model, LocalDate productionDate, double engine, boolean ac) {
		super();
		this.model = model;
		this.productionDate = productionDate;
		this.engine = engine;
		this.ac = ac;
	}
	
	public CarEntity(Car car) {  // constructor from car to car entity
		
		this(car.getModel(),car.getProductionDate(),car.getEngine(),car.isAc());
	}
	
	public Car getCar() {
		return new Car(model,productionDate,engine,ac);
	}
	
	
}
