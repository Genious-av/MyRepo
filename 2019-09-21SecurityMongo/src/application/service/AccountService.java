package application.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.documents.AccountDoc;
import application.repo.AccountRepo;
import dto.AccountDto;
@Service
public class AccountService implements IAccount {
	@Autowired
	AccountRepo accountRepo;
	
	List<String> roles=Arrays.asList(Roles.values()).stream()
			.map(role->role.toString())
			.collect(Collectors.toList());
	
	
	@Override
	public AccountingCodes addAccount(AccountDto account) {
		if(accountRepo.existsById(account.getUsername())) return AccountingCodes.UserExists;
		accountRepo.save(new AccountDoc(account.getUsername(), account.getPassword().hashCode()));
		return AccountingCodes.OK;
	}

	@Override
	public AccountingCodes removeAccount(String username) {
		if(!accountRepo.existsById(username)) return AccountingCodes.UserNotExist;
		accountRepo.delete(accountRepo.findById(username).orElse(null));
		return AccountingCodes.OK;
	}

	@Override
	public AccountingCodes updatePassword(String username, String password) {
		if(!accountRepo.existsById(username)) return AccountingCodes.UserNotExist;
		AccountDoc account=accountRepo.findById(username).orElse(null);
		if(account.isRevoked()==false)  return AccountingCodes.AccountIsRevoked;
		account.setPassword(password.hashCode());
		accountRepo.save(account);
		return AccountingCodes.OK;
	}

	@Override
	public AccountingCodes revokeAccount(String username) {
		if(!accountRepo.existsById(username)) return AccountingCodes.UserNotExist;
		AccountDoc account=accountRepo.findById(username).orElse(null);
		if(account.isRevoked()==false)  return AccountingCodes.AccountIsRevoked;
		account.setRevoked(false);
		accountRepo.save(account);
		return AccountingCodes.OK;
	}

	@Override
	public AccountingCodes activateAccount(String username) {
		if(!accountRepo.existsById(username)) return AccountingCodes.UserNotExist;
		AccountDoc account=accountRepo.findById(username).orElse(null);
		if(account.isRevoked()==true)  return AccountingCodes.AccountIsActive;
		account.setRevoked(true);
		accountRepo.save(account);
		return AccountingCodes.OK;
	}

	@Override
	public String getPasswordHash(String username) {
		if(!accountRepo.existsById(username)) return AccountingCodes.UserNotExist.getValue();
		Integer res=accountRepo.findById(username).orElse(null).getPassword();
		return 	res.toString();
	}

	@Override
	public LocalDateTime getActivationDate(String username) {
		if(!accountRepo.existsById(username)) return null;
		return accountRepo.findById(username).orElse(null).getActivationDate();
	}

	@Override
	public HashSet<String> getRoles(String username) {
		if(!accountRepo.existsById(username)) return null;
		return accountRepo.findById(username).orElse(null).getRoles();
	
	}

	@Override
	public AccountingCodes addRole(String username, String role) {
		System.out.println(roles.toString());
		if(!accountRepo.existsById(username)) return AccountingCodes.UserNotExist;
		if(!roles.contains(role)) return AccountingCodes.RoleIsUndefined;
		if(roles.contains(role)) return AccountingCodes.RoleIsAlreadySetted;
		AccountDoc account=accountRepo.findById(username).orElse(null);
		if(account.isRevoked()==false)  return AccountingCodes.AccountIsRevoked;
		 account.getRoles().add(role);
		 accountRepo.save(account);
		return AccountingCodes.OK;
	}

	@Override
	public AccountingCodes removeRole(String username, String role) {
		if(!accountRepo.existsById(username)) return AccountingCodes.UserNotExist;
		AccountDoc account=accountRepo.findById(username).orElse(null);
		 if(!account.getRoles().contains(role)) return AccountingCodes.NoRole;
		 if(account.isRevoked()==false)  return AccountingCodes.AccountIsRevoked;
		 account.getRoles().remove(role);
		 accountRepo.save(account);
		return AccountingCodes.OK;

	}
	
}
