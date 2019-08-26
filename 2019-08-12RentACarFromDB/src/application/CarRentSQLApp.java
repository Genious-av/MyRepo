package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import dto.Car;
import dto.Driver;
import dto.Model;
import dto.Record;
import service.RentCompanyService;

public class CarRentSQLApp {
	public static void main(String[] args) throws SQLException {
		try(
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/rent_company?useSSL=false&serverTimezone=UTC",
				"root",
				"12345");){
			
			RentCompanyService service= new RentCompanyService(connection);
			//System.out.println(service.getModel("Ford"));
			//System.out.println((service.addModel("Honda", 25, 80.)));
			
			//for(Model model: service.getAllModels()) System.out.println(model);
			
			//System.out.println(service.getDriver(111));
			//System.out.println(service.addDriver(666, "Leonid"));
			
			//for(Driver driver: service.getAllDrivers()) System.out.println(driver);
			
			
			//System.out.println(service.getAllCarsNotInUse());
			
			//System.out.println(service.getCar("AAA-111"));
			
			//System.out.println(service.addCar("XXX-111", service.getModel("Ford")));
			//System.out.println(service.getAllCarsGeneral(false));
			//System.out.println(service.setCarInUse(service.getCar("DDD-444"), true));
			
			//System.out.println(service.rentRecord(service.getCar("BBB-222"), service.getDriver(444), LocalDate.of(2019, 7, 20), 3));
			
			
			//System.out.println(service.returnRecord(service.getCar("BBB-222"), LocalDate.of(2019, 7, 20), 3));
			
			//System.out.println(service.getAllRecords(LocalDate.of(2019, 1, 1), LocalDate.of(2020, 6, 12)));
			
			//for(Record r: service.getAllopenRecords()) System.out.println(r);
			//System.out.println(service.removeRecord(27));
			//System.out.println(service.getAllDelayedRecords());
			
			//for(Car car: service.getAllCarsbyDriversAndTank("Sasha",30)) System.out.println(car);
			
			System.out.println(service.getMostProfitableModel(LocalDate.of(2019, 1, 20), LocalDate.of(2019, 10, 20)));
		}
	}
	
}
