package telran.security.services;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import telran.security.dto.AccountDto;
import telran.security.dto.AccountingCodes;
import telran.security.entities.AccountEntity;
import telran.security.entities.Roles;
import telran.security.repositories.AccountRepository;


@Service
public class AuthService implements IAuthService {

    public static final int MIN_LENGTH = 6;

    public static final int MAX_LENGTH = 10;
    @Autowired
	 private AccountRepository accountRepository;
    
    @Autowired
     private ModelMapper modelMapper;
    
    @Autowired
     private PasswordEncoder passwordEncoder;
    
    @Autowired
    public AuthService() {
        
    }

    @Override
    public AccountingCodes addAccount(AccountDto account) {
        AccountEntity accountEntity = modelMapper.map(account, AccountEntity.class); //transforming dto to entity
        
        accountEntity.getRoles().add(Roles.USER.toString()); //adding to roles User role
        accountEntity.setActivationDate(LocalDateTime.now());
        accountEntity.setPassword(passwordEncoder.encode(account.getPassword())); // encoding password
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    @Override
    public AccountingCodes removeAccount(String username) {
        if (accountRepository.deleteByUsername(username) == 0) {
            return AccountingCodes.ACCOUNT_NOT_EXISTS;
        }
        return AccountingCodes.OK;
    }

    @Override
    public AccountingCodes updatePassword(String username, String password) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null); //getting account by username
        AccountingCodes result=AccountingCodes.WRONG_PASSWORD;
        if (Objects.isNull(accountEntity)) {
            return AccountingCodes.ACCOUNT_NOT_EXISTS;
        }

        if(validPassword(accountEntity.getPassword(),password)) {
        	accountEntity.setPassword(passwordEncoder.encode(password));
        	accountEntity.setActivationDate(LocalDateTime.now());
        	 accountRepository.save(accountEntity);
        	 result=AccountingCodes.OK;
        }
       
        return result;
    }

    private boolean validPassword(String oldPasswordHash, String password) {
    	int length=password.length();
		if(length<MIN_LENGTH || length>MAX_LENGTH || passwordEncoder.matches(password, oldPasswordHash)) return false;
		return true;
	}

	@Override
    public AccountingCodes revokeAccount(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return AccountingCodes.ACCOUNT_NOT_EXISTS;
        }
        accountEntity.setRevoked(true);
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    @Override
    public AccountingCodes activateAccount(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return AccountingCodes.ACCOUNT_NOT_EXISTS;
        }
        accountEntity.setRevoked(false);
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    @Override
    public String getPasswordHash(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
//        if (Objects.isNull(accountEntity) || accountEntity.isRevoked()) {
//            return null;
//        }
        return accountEntity.getPassword();
    }

    @Override
    public LocalDateTime getActivationDate(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return null;
        }
        return accountEntity.getActivationDate();
    }

    @Override
    public HashSet<String> getRoles(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return null;
        }
        return new HashSet<>(accountEntity.getRoles());
    }

    @Override
    public AccountingCodes addRole(String username, String role) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return null;
        }
        List<String> roles=accountEntity.getRoles();
        if(roles.contains(role)) return AccountingCodes.ROLE_ALREADY_EXISTS;
        roles.add(role);
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    @Override
    public AccountingCodes removeRole(String username, String role) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return null;
        }
        accountEntity.getRoles().remove(role);
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    public boolean isUsernameAlreadyInUse(String username) {
        return accountRepository.existsByUsername(username);
    }

    public AccountEntity getUserByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

}
