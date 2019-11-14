package telran.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import telran.aop.logs.aspects.LogAspect;


@Configuration
@ComponentScan(basePackages="telran.aop")
@EnableMongoRepositories("telran.aop.repo") //here contains mongoRepository
@Order(value=30) //base order 100, 200>100 consequently this config is more priority

public class LibraryLoggingAspect {
	@Bean
	LogAspect getAspect() {
		return new LogAspect();
	}
}
