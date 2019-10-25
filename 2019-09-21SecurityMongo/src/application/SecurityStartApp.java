package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@SpringBootApplication
public class SecurityStartApp {
	public static void main(String[] args) {
		SpringApplication.run(SecurityStartApp.class,args);



	}
	
}
