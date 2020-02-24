package telran.aop.logs.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


//here contains all annotations and links to module logging
@Configuration
@ComponentScan(basePackages="telran.aop")
@EnableMongoRepositories("telran.aop.repo") //here contains mongoRepository
@Order(value=50) //base order 100, 200>100 consequently this config is more priority
public class LoggingConfigure {

	

}
