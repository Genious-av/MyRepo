package telran.logs.processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.logs.processing.repo.LogsRepo;

@SpringBootApplication
public class LogsMongoProcessingAppl {

	public static void main(String[] args) {
				SpringApplication.run(LogsMongoProcessingAppl.class, args);
		
	}

}
