package application.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.service.AccountingCodes;
import application.service.IAccount;
import dto.AccountDto;

@RestController
public class Controller {
	@Autowired
	IAccount accountService;
	
	@PostMapping("/addAccount")
	ResponseEntity<AccountingCodes>  addAccount(@RequestBody AccountDto account) {
		AccountingCodes code=accountService.addAccount(account);
		if(!code.equals(AccountingCodes.OK)) {
			return new ResponseEntity<AccountingCodes>(code, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<AccountingCodes>(code, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAccount")
	ResponseEntity<AccountingCodes> removeAccount(@RequestParam String username) {
		AccountingCodes code=accountService.removeAccount(username);
		if(!code.equals(AccountingCodes.OK)) {
			return new ResponseEntity<AccountingCodes>(code, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<AccountingCodes>(code, HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword")
	ResponseEntity<AccountingCodes> updatePassword(@RequestParam String username, @RequestParam String password) {
		AccountingCodes code=accountService.updatePassword(username, password);
		if(!code.equals(AccountingCodes.OK)) {
			return new ResponseEntity<AccountingCodes>(code, HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<AccountingCodes>(code, HttpStatus.OK);
	}
	
	@PutMapping("/revokeAccount")
	ResponseEntity<AccountingCodes>  revokeAccount(@RequestParam String username) {
		AccountingCodes code=accountService.revokeAccount(username);
		if(!code.equals(AccountingCodes.OK)) {
			return new ResponseEntity<AccountingCodes>(code, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<AccountingCodes>(code, HttpStatus.OK);
	}
	
	@PutMapping("/activateAccount")
	ResponseEntity<AccountingCodes>  activateAccount(@RequestParam String username) {
		AccountingCodes code=accountService.activateAccount(username);
		if(!code.equals(AccountingCodes.OK)) {
			return new ResponseEntity<AccountingCodes>(code, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<AccountingCodes>(code, HttpStatus.OK);
	}
	
	@GetMapping("/getPasswordHash")
	ResponseEntity<String> getPasswordHash(@RequestParam String username) {
		String res=accountService.getPasswordHash(username);
		if(res.equals(AccountingCodes.UserNotExist.getValue())) {
			return new ResponseEntity<String>(AccountingCodes.UserNotExist.getValue(), HttpStatus.FORBIDDEN);
		}
	return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getActivationDate")
	ResponseEntity<LocalDateTime> getActivationDate(@RequestParam String username) {
		LocalDateTime date=accountService.getActivationDate(username);
		if(date.equals(null)) return new ResponseEntity<LocalDateTime>(HttpStatus.BAD_REQUEST);
	return 	new ResponseEntity<LocalDateTime>(date, HttpStatus.OK);
	}
	
	@GetMapping("/getRoles")
	ResponseEntity<HashSet<String>> getRoles(String username){
		HashSet<String> res=accountService.getRoles(username);
		if(res.equals(null)) return new ResponseEntity<HashSet<String>>(HttpStatus.BAD_REQUEST);
		return 	new ResponseEntity<HashSet<String>>(res, HttpStatus.OK);	
	}
	
	@PutMapping("/addRole")
	ResponseEntity<AccountingCodes>  addRole(String username, String role) {
		AccountingCodes code=accountService.addRole(username, role);
		if(!code.equals(AccountingCodes.OK)) {
			return new ResponseEntity<AccountingCodes>(code, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<AccountingCodes>(code, HttpStatus.OK);
	}
	
	@PutMapping("/removeRole")
	ResponseEntity<AccountingCodes>  removeRole(String username, String role) {
		AccountingCodes code=accountService.addRole(username, role);
		if(!code.equals(AccountingCodes.OK)) {
			return new ResponseEntity<AccountingCodes>(code, HttpStatus.FORBIDDEN);
		}
	return new ResponseEntity<AccountingCodes>(code, HttpStatus.OK);
	}
	
}
