package application.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import application.security.entities.Account;
import application.security.entities.Role;
import application.security.services.ISecurityService;

// Spring security get user-password from DB
@Component
public class UserLoader implements UserDetailsService{
	
	@Autowired
	ISecurityService securityService;

	@Override
	@Transactional(readOnly = true) // required for string 30 (read only for performance)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException 
	{
		Account account = securityService.getAccount(login);
		if(account == null) throw new UsernameNotFoundException("Login not registered");
		
		
		Set<Role> roles = account.getRoles();
		
		return new User(login, "{noop}" + account.getPassword(), // {noop} - not encrypted password
				// convert roles set to Array
						AuthorityUtils.createAuthorityList(
									roles.stream()
									.map(Role::getRoleString)
									.collect(Collectors.toList())
									.toArray(new String[roles.size()])));
	}
}
