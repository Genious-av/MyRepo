package telran.library.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import telran.security.auth.Authentificator;

import telran.security.services.AuthService;

//here contains all annotations and links to module security
@Configuration
@ComponentScan(basePackages="telran.security")
@EnableMongoRepositories("telran.security.repositories") //here contains mongoRepository
@Order(value=200) //base order 100, 200>100 consequently this config is more priority
public class LibraryBooksConfigure extends WebSecurityConfigurerAdapter {



}
