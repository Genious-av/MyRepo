package telran.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //putting to application context bicrypt encoder
public class AccountingWebSecurityConfig {
	@Bean //annotation @bean can be only in class that is in application context
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
