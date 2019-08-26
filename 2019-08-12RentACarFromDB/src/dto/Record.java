package dto;

import java.time.LocalDate;

public class Record {
	
	private int id;
	private Car car;
	private Driver driver;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int rentDays;
	private int tankPercent;
	private double cost;
	
	public Record() {
		super();
	}

	public Record(int id, Car car, Driver driver, LocalDate rentDate, LocalDate returnDate, int rentDays,
			int tankPercent, double cost) {
		super();
		this.id = id;
		this.car = car;
		this.driver = driver;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.rentDays = rentDays;
		this.tankPercent = tankPercent;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getRentDays() {
		return rentDays;
	}

	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}

	public int getTankPercent() {
		return tankPercent;
	}

	public void setTankPercent(int tankPercent) {
		this.tankPercent = tankPercent;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", car=" + car + ", driver=" + driver + ", rentDate=" + rentDate + ", returnDate="
				+ returnDate + ", rentDays=" + rentDays + ", tankPercent=" + tankPercent + ", cost=" + cost + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + id;
		result = prime * result + ((rentDate == null) ? 0 : rentDate.hashCode());
		result = prime * result + rentDays;
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + tankPercent;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (id != other.id)
			return false;
		if (rentDate == null) {
			if (other.rentDate != null)
				return false;
		} else if (!rentDate.equals(other.rentDate))
			return false;
		if (rentDays != other.rentDays)
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (tankPercent != other.tankPercent)
			return false;
		return true;
	}
	
	
	
	
	

}
