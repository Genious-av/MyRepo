package application.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum AccountingCodes {
	
	
	OK,
	UserExists,
	UserNotExist("User not exist"),
	RoleAlreadyExists,
	AccountIsRevoked,
	AccountIsActive,
	RoleIsUndefined,
	NoRole,
	RoleIsAlreadySetted;
	
	
	private String value;
	
	
}
