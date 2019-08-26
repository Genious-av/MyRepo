package service;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import dto.Car;
import dto.Driver;
import dto.Model;
import dto.Record;

public class RentCompanyService implements IRentCompany{
	private Connection connection;
	
	private Statement statement=null;

	public RentCompanyService(Connection connection) throws SQLException {
		super();
		this.connection = connection;
		statement=connection.createStatement();
	}

	@Override
	public boolean addCar(String regNumber, Model model) throws SQLException {
		String sql="INSERT IGNORE INTO car VALUES ("+
				"'"+regNumber+"'"+","+"'"+
						model.getModelName()+"'"+","+0+
					")";
		return statement.executeUpdate(sql)==1;
	}

	@Override
	public Car getCar(String regNumber) throws SQLException {
		String sql="SELECT*FROM car WHERE id_car='"+regNumber+"'";
		ResultSet resultSet= statement.executeQuery(sql);
		if(!resultSet.next()) return null;
		String id=resultSet.getString(1);
		Boolean inUse=resultSet.getBoolean(3);
		Model model=getModel(resultSet.getString(2));
		Car car=new Car(id, model,inUse);
		return car;
	}

	@Override
	public Set<Car> getAllCarsGeneral(boolean flagInUse) throws SQLException {
		String sql="SELECT*FROM car WHERE in_use="+((flagInUse)? 1:0);
		ResultSet resultSet=connection.createStatement().executeQuery(sql);
		Set<Car> result = new HashSet<Car>();
		while(resultSet.next()){
			
			result.add(new Car(resultSet.getString(1),getModel(resultSet.getString(2)),resultSet.getBoolean(3)));
		}
		return result;
	}

	@Override
	public Set<Car> getAllCarsNotInUse() throws SQLException {
		
		return getAllCarsGeneral(false);
	}
	@Override
	public boolean  setCarInUse(Car car, boolean inUse) throws SQLException {
		String sql="UPDATE car SET in_use="+(inUse?1:0)+" WHERE id_car="+"'"+car.getRegNumber()+"'";
		
		return connection.createStatement().executeUpdate(sql)==1;
	};
	
	
	@Override
	public boolean addModel(String modelName, int tank, double dayPrice) throws SQLException {
	String sql="INSERT IGNORE INTO model VALUES ("+
	"'"+modelName+"'"+","+
			tank+","+
			dayPrice+")";
		return statement.executeUpdate(sql)==1;
	}

	@Override
	public Model getModel(String modelName) throws SQLException {
		String sql="SELECT*FROM model WHERE id_model='"+modelName+"'";
		ResultSet resultSet= statement.executeQuery(sql);
		if(!resultSet.next()) return null;
		
		
		return new Model(resultSet.getString(1),resultSet.getInt(2),resultSet.getDouble(3));
	}

	@Override
	public Set<Model> getAllModels() throws SQLException {
		String sql="SELECT*FROM model";
		ResultSet resultSet=statement.executeQuery(sql);
		Set<Model> result = new HashSet<Model>();
		while(resultSet.next()){
			result.add(new Model(resultSet.getString(1),resultSet.getInt(2),resultSet.getDouble(3)));
		}
		return result;
	}

	@Override
	public boolean addDriver(int id, String name) throws SQLException {
		String sql="INSERT IGNORE INTO driver VALUES ("+
					id+","+
				"'"+name+"'"+")";
	return statement.executeUpdate(sql)==1;
		
	}

	@Override
	public Driver getDriver(int id) throws SQLException {
		String sql="SELECT*FROM driver WHERE id_driver="+id;
		ResultSet resultSet= statement.executeQuery(sql);
		if(!resultSet.next()) return null;
		return new Driver(resultSet.getInt(1),resultSet.getString(2));
	}

	@Override
	public Set<Driver> getAllDrivers() throws SQLException {
		String sql="SELECT*FROM driver";
		ResultSet resultSet=statement.executeQuery(sql);
		Set<Driver> result = new HashSet<Driver>();
		while(resultSet.next()){
			result.add(new Driver(resultSet.getInt(1),resultSet.getString(2)));
		}
		return result;
	}

	@Override
	public int rentRecord(Car car, Driver driver, LocalDate rentDate, int rentDays) throws SQLException {
		
		if(getCar(car.getRegNumber()).isInUse()==true) return -1;
		
		Double cost=getCost(car);
			
				
		 String sql="INSERT INTO record (id_car,id_driver,rent_date,rent_days,cost) VALUES ("+
				 "'"+car.getRegNumber()+"'"+","+"'"+driver.getId()+"'"+" ,"+"'"+java.sql.Date.valueOf(rentDate)+"'"+" ,"+rentDays+" ,"+rentDays*cost+")";
		setCarInUse(car, true);
		 statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
		 ResultSet rs=statement.getGeneratedKeys();
		rs.next();
		 return	rs.getInt(1);
				
	}

	@Override
	public int returnRecord(Car car, LocalDate returnDate, int tankPercent) throws SQLException {
		
		String sql="SELECT * FROM record WHERE id_car="+"'"+car.getRegNumber()+"'";
		ResultSet resultSet=statement.executeQuery(sql);
		int rentDaysNew=0;
		while(resultSet.next()){
			Date rentDate=resultSet.getDate(4);
			LocalDate rentDateld=Instant.ofEpochMilli(rentDate.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
			Period period=Period.between(rentDateld, returnDate);
			rentDaysNew=period.getDays();
		}
		
		Double cost=getCost(car);
		
		
		sql="UPDATE record SET return_date="+"'"+java.sql.Date.valueOf(returnDate)+"'"+" ,tank_persent="+tankPercent+" ,rent_days="+rentDaysNew+ ", cost="+rentDaysNew*cost;
		setCarInUse(car, false);
		return statement.executeUpdate(sql);
	}
	
	public double getCost (Car car) throws SQLException {
		String sql="SELECT*FROM model WHERE id_model="+"'"+car.getModel().getModelName()+"'";
		ResultSet resultSet=connection.createStatement().executeQuery(sql);
		long price=0;
		while(resultSet.next()){
			price=resultSet.getLong(3);
			
		}
		return price;
	}

	@Override
	public Set<Record> getAllRecords(LocalDate from, LocalDate to) throws SQLException {
		String sql="SELECT*FROM record WHERE rent_date BETWEEN "+"'"+java.sql.Date.valueOf(from)+"'"+" AND "+"'"+java.sql.Date.valueOf(to)+"'";
		ResultSet resultSet=connection.createStatement().executeQuery(sql);
		Set<Record> result = new HashSet<Record>();
		while(resultSet.next()){
			
			LocalDate rentDateLD=Instant.ofEpochMilli(resultSet.getDate(4).getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
			LocalDate returnDateLD=Instant.ofEpochMilli(resultSet.getDate(5).getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
			result.add(new Record(resultSet.getInt(1),
									getCar(resultSet.getString(2)),
									getDriver(resultSet.getInt(3)),
									rentDateLD,
									returnDateLD,
									resultSet.getInt(6),
									resultSet.getInt(7),
									resultSet.getDouble(8)));
		}
		return result;
	}

	@Override
	public Set<Record> getAllopenRecords() throws SQLException {
		String sql="SELECT*FROM record WHERE return_date IS NULL";
		ResultSet resultSet=connection.createStatement().executeQuery(sql);
		Set<Record> result = new HashSet<Record>();
		while(resultSet.next()){
			
			LocalDate rentDateLD=Instant.ofEpochMilli(resultSet.getDate(4).getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
			LocalDate returnDateLD=null;
			result.add(new Record(resultSet.getInt(1),
									getCar(resultSet.getString(2)),
									getDriver(resultSet.getInt(3)),
									rentDateLD,
									returnDateLD,
									resultSet.getInt(6),
									resultSet.getInt(7),
									resultSet.getDouble(8)));
		}
		return result;
	}

	@Override
	public boolean removeRecord(int id) throws SQLException {
	String sql="DELETE FROM record WHERE id_record="+id;
	
		return connection.createStatement().executeUpdate(sql)==1;
	}

	@Override
	public Set<Record> getAllDelayedRecords() throws SQLException {
		LocalDate ldNow=LocalDate.now();
		String sql="SELECT*FROM record WHERE return_date IS NULL AND DATE_ADD(rent_date, INTERVAL rent_days DAY)<"+"'"+java.sql.Date.valueOf(ldNow)+"'";
		ResultSet resultSet=connection.createStatement().executeQuery(sql);
		Set<Record> result = new HashSet<Record>();
		while(resultSet.next()){
			System.out.println("works");
			LocalDate rentDateLD=Instant.ofEpochMilli(resultSet.getDate(4).getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
			LocalDate returnDateLD=null;
			result.add(new Record(resultSet.getInt(1),
									getCar(resultSet.getString(2)),
									getDriver(resultSet.getInt(3)),
									rentDateLD,
									returnDateLD,
									resultSet.getInt(6),
									resultSet.getInt(7),
									resultSet.getDouble(8)));
		}
		return result;
	}

	@Override
	public Model getMostProfitableModel(LocalDate from, LocalDate to) throws SQLException {
		String sql="SELECT id_model, tank_model, day_price_model,  SUM(cost) from record natural join(select*from car) as cars natural join(select*from model) as models  where rent_date BETWEEN'"+java.sql.Date.valueOf(from)+"' AND '"+ java.sql.Date.valueOf(to)+"' GROUP BY id_model ORDER BY SUM(cost) DESC LIMIT 1";
		
		ResultSet resultSet=connection.createStatement().executeQuery(sql);
		resultSet.next();
		
		
	return new Model(resultSet.getString(1),resultSet.getInt(2),resultSet.getDouble(3));
	}

	@Override
	public Set<Car> getAllCarsbyDriversAndTank(String driverName, int tank) throws SQLException {
		String sql="select*from record natural join(select*from driver where name_driver='"+ driverName+"') as driver natural join(select*from car natural join model where tank_model>="+tank+ ")as cars";
		ResultSet resultSet=connection.createStatement().executeQuery(sql);
		Set<Car> result = new HashSet<Car>();
		while(resultSet.next()){
			
			result.add(new Car(resultSet.getString(1), getModel(resultSet.getString(10)), resultSet.getBoolean(11)));
			
		}
		
		return result;
	}

	
	
	
	
	

	
	
}
