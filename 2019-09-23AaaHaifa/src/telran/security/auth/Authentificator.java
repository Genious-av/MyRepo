package telran.security.auth;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import telran.security.services.IAuthService;
@Configuration
public class Authentificator implements UserDetailsService {
	@Autowired
	IAuthService authService;

	@Value("${password_period:1000000}") //period of living in minutes
	int passwordPeriod;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String hashcode=null;
		if((hashcode=authService.getPasswordHash(username))==null) {
			throw new UsernameNotFoundException("");
		}
		LocalDateTime activationDate=authService.getActivationDate(username);
		
		if(ChronoUnit.MINUTES.between(activationDate, LocalDateTime.now())>passwordPeriod)
			throw new UsernameNotFoundException("expired password period");
		
		String[] roles=authService.getRoles(username).stream()
													.map(r->"ROLE_"+r).toArray(String[]::new);
				
				
		return new User(username,hashcode,AuthorityUtils.createAuthorityList(roles));
	}

}
