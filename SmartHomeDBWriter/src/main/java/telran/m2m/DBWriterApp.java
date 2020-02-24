package telran.m2m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DBWriterApp {

	public static void main(String[] args)
	{
		SpringApplication.run(DBWriterApp.class, args);
		System.out.println("Hello from DBWriterApp");
	}
}
