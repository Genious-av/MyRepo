package application.tinkoff;



import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
/** @author Alexander Gryaznov
 * @version 0.2
 * Spring starter class  */

@SpringBootApplication
public class AssignApplication {
	 private static final Logger log = Logger.getLogger(AssignApplication.class);
	 
		public static void main(String[] args)  {
			SpringApplication.run(AssignApplication.class, args);	
			log.info("Spring boot application started");
	}
}
