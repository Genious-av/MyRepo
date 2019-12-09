package telran.m2m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SensorGenerationApp {
	
	 private static final long TIMEOUT = 60000;

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext ctx=SpringApplication.run(SensorGenerationApp.class, args);
		Thread.sleep(TIMEOUT);
		ctx.close();
	}
 
}
