package application.documents;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter

@Document(collection="accounts")
public class AccountDoc {
	@Id
	String username;
	int password;
	HashSet<String> roles=new HashSet<>();
	boolean revoked=true;
	LocalDateTime activationDate;
	
	
	public AccountDoc(String username, int password) {
		super();
		this.username = username;
		this.password = password;
		this.revoked=true;
		this.activationDate=LocalDateTime.now();
	}
	
	
	
	
}

