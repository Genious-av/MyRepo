package application.security.services;

import application.security.entities.Account;

public interface ISecurityService {
	
	Account getAccount(String login);
	boolean addAccount(String login, String password, String roleString);
	void removeAccount(String login);
	boolean addRole(String roleString); 
	boolean grantRole(String login, String roleString);
	boolean depriveRole(String login, String roleString);
	String[] getRoles(String login);
	public String[] getLogins(String roleString);
}
