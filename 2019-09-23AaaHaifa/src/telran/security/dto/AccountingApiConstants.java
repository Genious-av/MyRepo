package telran.security.dto;

public interface AccountingApiConstants {

	
	String ADD_ACCOUNT = "/accounts";
	String DELETE_ACCOUNT ="/accounts/remove";
	String UPDATE_PASSWORD = "/accounts/password";
	String REVOKE_PASSWORD = "/accounts/revoke";
	String ACTIVATE_ACCOUNT = "/accounts/activate";
	String ADD_ROLE = "/accounts/role/add";
	String REMOVE_ROLE = "/accounts/role/remove";
	String GET_HASH_CODE="/accounts/get_hash";
}
