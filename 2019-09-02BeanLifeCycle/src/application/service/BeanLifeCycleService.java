package application.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class BeanLifeCycleService {
	@Value("${secrecy_path}")
	private String path;
	private HashMap<String,String> secrecy = new HashMap<>();
	
	@PostConstruct
	private void setSecrecy() throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(path));
		String line=null;
		String[] data=null;
		while(true) {
			line=br.readLine();
			if(line==null) break;
			data=line.split(":");
			secrecy.put(data[0], data[1]);
		}
	}
	public String check(String login, String password) {
		String psw=secrecy.get(login);
		if(psw==null || !psw.equals(password)) return "Acccess denied";
		return "Access allowed";
	}
	
}
