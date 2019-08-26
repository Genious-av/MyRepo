package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

import dto.Car;
import dto.Driver;
import dto.Model;
import dto.Record;

public interface IRentCompany {
	boolean addCar(String regNumber, Model model) throws SQLException;
	Car getCar(String regNUmber) throws SQLException;
	Set<Car> getAllCarsGeneral(boolean flagInUse) throws SQLException;
	Set<Car> getAllCarsNotInUse() throws SQLException;
	boolean setCarInUse(Car car, boolean inUse) throws SQLException;
	
	boolean addModel(String modelName, int tank, double dayPrice) throws SQLException;
	Model getModel(String modelName) throws SQLException;
	Set<Model> getAllModels() throws SQLException;
	
	boolean addDriver(int id,String name) throws SQLException;
	Driver getDriver(int id) throws SQLException;
	Set<Driver> getAllDrivers() throws SQLException;
	
	
	int rentRecord(Car car, Driver driver, LocalDate rentDate, int rentDays) throws SQLException;
	int  returnRecord(Car car,LocalDate returnDate,int tankPercent) throws SQLException;
	
	Set<Record> getAllRecords(LocalDate from,LocalDate to) throws SQLException;
	Set<Record> getAllopenRecords() throws SQLException;
	boolean removeRecord(int id) throws SQLException;
	Set<Record> getAllDelayedRecords() throws SQLException;
	Model getMostProfitableModel(LocalDate from,LocalDate to) throws SQLException;
	
	 Set<Car> getAllCarsbyDriversAndTank(String driverName, int tank) throws SQLException;
	
}
