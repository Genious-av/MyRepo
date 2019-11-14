package telran.library.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import telran.security.auth.Authentificator;
import telran.security.dto.AccountingApiConstants;
import telran.security.entities.Roles;
import telran.security.services.AuthService;

//here contains all annotations and links to module security
@Configuration
@ComponentScan(basePackages="telran.security")
@EnableMongoRepositories("telran.security.repositories") //here contains mongoRepository
@Order(value=20) //base order 100, less number - more priority
public class LibraryBooksConfigure extends WebSecurityConfigurerAdapter {

	@Value("${authentication:enabled}")
	String authentication;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		
		
		http.httpBasic(); //BASE64 type of encoding
		http.csrf().disable();//disable token csrf
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //never to save authentification session between requests
		if(authentication.equals("enabled")) {
			http.authorizeRequests().anyRequest().authenticated(); 	
			
			//http.authorizeRequests().anyRequest().permitAll();
		} else {
			http.authorizeRequests().anyRequest().permitAll();
		}
		
	}

}
