package application;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONPlaceHolderApp {

	public static void main(String[] args) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		
		
	
		
		/*
		 //getting response from server GET
		
		HttpEntity<String> httpEntity = new HttpEntity<>(null); //null used to parametrize HttpEntity with string otherwize httpEntity doesn`t instanced
		String url="http://jsonplaceholder.typicode.com/posts?id={id}&userId={userId}";
		 Map<String,Integer> uriVariables = new HashMap<String, Integer>();
		 uriVariables.put("id", 29);
		 uriVariables.put("userId", 3);
		 
		 
		 //exchange uses JACKSON to convert response from JSON
		 ResponseEntity<Post[]> response=
		 restTemplate.exchange(url, HttpMethod.GET, httpEntity,Post[].class,uriVariables); // where, what method, where to put, what type of responce, uri parameters
		 System.out.println(response);
		 System.out.println(Arrays.toString(response.getBody()));
		 */	
	
		// method POST
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json");
		Post post = new Post(29, 3,"Haifa2019","the best of the best"); // create new Post
		String body=new ObjectMapper().writeValueAsString(post);
		HttpEntity<String> httpEntity=new HttpEntity<>(body,headers); //create new entity
System.out.println(httpEntity);
		String url="http://jsonplaceholder.typicode.com/posts";
		
		ResponseEntity<Post> response=
				restTemplate.exchange(url, HttpMethod.POST, httpEntity,Post.class);
		
		System.out.println(response.getBody());
		
	}

}
