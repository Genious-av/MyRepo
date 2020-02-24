package telran.aop.logs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import telran.aop.logs.aspects.LogAspect;

@Configuration
public class AopConfig {
	@Bean
	LogAspect getAspect() {
		return new LogAspect();
	}
}
