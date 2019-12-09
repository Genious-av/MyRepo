package weather.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class WeatherServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApp.class, args);
	}

}
