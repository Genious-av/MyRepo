package assign.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ComponentScan({ 
	  "assign.moduleWeather.*",
	  "assign.moduleTimeZone.*",
	  "assign.server.*",
	  "telran.aop.*"
	 	})
public class AssignServerApp {

	public static void main(String[] args) {
		SpringApplication.run(AssignServerApp.class, args);
	}

}
