package telran.security.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class LocaleConfig {

    @PostConstruct //this anno is used to create a bean after all dependencies has been created
    public void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        System.out.println("Date in UTC: " + new Date().toString());
    }
}