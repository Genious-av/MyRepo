package telran.security.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import telran.security.dto.AccountingApiConstants;

import telran.security.entities.Roles;

@Configuration
public class AuthWebConfig extends WebSecurityConfigurerAdapter {
	@Value("${authentication:enabled}")//@value gives dependency injection. Spring looks for default value of authentification
	String authentication;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic(); //BASE64 type of encoding
		http.csrf().disable();//disable token csrf
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //never to save authentification session between requests
		if(authentication.equals("enabled")) {
			
						http.authorizeRequests().anyRequest().authenticated(); 		 //all request should be authentificated
//			http.authorizeRequests().antMatchers(AccountingApiConstants.ADD_ACCOUNT, AccountingApiConstants.DELETE_ACCOUNT).authenticated(); //for this actions any authorised user can perform
//			http.authorizeRequests().antMatchers(AccountingApiConstants.UPDATE_PASSWORD,AccountingApiConstants.REVOKE_PASSWORD,AccountingApiConstants.ACTIVATE_ACCOUNT)
//					.hasRole(Roles.ADMIN.toString());//this actions can perform only admin
//			http.authorizeRequests().antMatchers(AccountingApiConstants.ADD_ROLE,AccountingApiConstants.REMOVE_ROLE).hasAnyRole(Roles.ADMIN.toString(),Roles.SUPERUSER.toString());
//			http.authorizeRequests().antMatchers(AccountingApiConstants.GET_HASH_CODE).permitAll();
		} else {
			http.authorizeRequests().anyRequest().permitAll();
		}
		

		
	}

}
