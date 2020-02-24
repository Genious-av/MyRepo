package application.security.services;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.security.entities.Account;
import application.security.entities.Role;
import application.security.repositories.AccountRepository;
import application.security.repositories.RoleRepository;

@Transactional
@Service
public class SecurityService implements ISecurityService {
	
	@Autowired AccountRepository accountRepo;
	@Autowired RoleRepository roleRepo;
	
	@Override
	public boolean addAccount(String login, String password, String roleString) {
		if (accountRepo.existsById(login)) return false;
		
		if (!roleRepo.findById("ROLE " + roleString).isPresent()) 
			 roleRepo.save(new Role("ROLE_" + roleString));
		
		accountRepo.save(new Account(login, password, "ROLE_"+roleString));
		return true;
	}
		
	@Override
	public boolean addRole(String roleString) {
		if (roleRepo.existsById("ROLE_"+roleString)) return false;
		roleRepo.save(new Role(roleString));
		return true;
	}
	
	@Override
	public boolean grantRole(String login, String roleString) {
		
		Account account = accountRepo.findById(login).orElse(null);
		Role role = roleRepo.findById("ROLE_"+roleString).orElse(null);
		
		if (account == null || role == null) return false;
		
		account.getRoles().add(role);
		
		accountRepo.save(account);
		return true;
	}
	
	@Override
	public boolean depriveRole(String login, String roleString) {
		
		Account account = accountRepo.findById(login).orElse(null);
		Role role = roleRepo.findById("ROLE_"+roleString).orElse(null);
		
		if (account == null || role == null) return false;
		boolean result = account.getRoles().remove(role);
		
		if (result) 
		{
			accountRepo.save(account);
			if (account.getRoles().size() == 0) accountRepo.delete(account);	
		}
		
		return result;
	}
	
	@Override
	public String[] getRoles(String login) {
		Account account = accountRepo.findById(login).orElse(null);
		
		if (account != null)
		{
			Set<Role> roles = account.getRoles();
			return roles.stream().map(role -> role.getRoleString().substring(5))
					.collect(Collectors.toList()).toArray(new String[roles.size()]);
		}
		
		return new String[0];

	}
	@Override
	public Account getAccount(String login) {
		return accountRepo.findById(login).orElse(null);
	}
	
	public String[] getLogins(String roleString) {
		Role role = roleRepo.findById("ROLE_"+roleString).orElse(null);
		if (role != null) {
			Set<Account> accounts = role.getAccounts();
			return accounts.stream().map(account -> account.getLogin())
					.collect(Collectors.toList()).toArray(new String[accounts.size()]);
		}
		return new String[0];
	}

	@Override
	public void removeAccount(String login) {
		accountRepo.deleteById(login);
	}

	

}
