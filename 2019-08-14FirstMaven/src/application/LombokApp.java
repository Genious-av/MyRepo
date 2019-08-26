package application;

import dto.Car;

public class LombokApp {
		public static void main(String[] args) {
			Car car = new Car("Volvo", 2019, 2., true);
			System.out.println(car);
			System.out.println(car.getModel());
		}
}
