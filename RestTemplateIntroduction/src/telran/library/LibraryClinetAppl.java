package telran.library;

import org.springframework.cglib.transform.impl.AddPropertyTransformer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import telran.library.dto.Book;
import telran.library.dto.LibReturnCode;
import telran.library.dto.PublisherAuthor;
import telran.library.dto.Reader;
import telran.library.random.RandomLib;

import  static telran.library.api.LibraryApiConstants.*;

import java.time.LocalDate;
import java.util.Base64;
import telran.library.random.*;

public class LibraryClinetAppl {
	
	
	
	 public static void main(String[] args) {
						
		RandomGen generate=new RandomGen();
		
		generate.dynamicGen();
		
	}
	 
	 
}
