package application;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class Base64App {
	
	private static final String LOGIN = "avgrya";
	private static final String PASSWORD = "12345";

	public static void main(String[] args) {
	 HttpHeaders headers = new HttpHeaders();
	 String auth=LOGIN+":"+PASSWORD;

	 String base64=Base64.getEncoder().encodeToString(auth.getBytes()); //BASIC - shows that this is basic type of encoding
	 System.out.println(base64);
	 headers.add("Authorization", "Basic "+base64); // put authorization info to header
	 HttpEntity httpEntity = new HttpEntity(headers);
	 
	String authReceived=httpEntity.getHeaders().getValuesAsList("Authorization").get(0);
	System.out.println(authReceived);
	
	String decoded=new String(Base64.getDecoder().decode(authReceived.substring(authReceived.indexOf(' ')+1))); //getting String from byte[]
	System.out.println(decoded);
	}

}
