package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.Child;
import dto.Employee;
import dto.Person;

public class JacksonInheritanceApp {

	public static void main(String[] args) throws IOException {
		Person[] persons= {
				new Child("Dodik", LocalDate.of(2014, 11, 07),"Creek"),
				new Employee("Vasya",LocalDate.of(1994, 1, 23), 7600.)};
		
		System.out.println(Arrays.toString(persons));
		
		ObjectMapper mapper= new ObjectMapper();
		mapper.registerModule(new JavaTimeModule()); //used to  conver data in format LOCALDATE appropriately
		String json=mapper.writeValueAsString(persons);
		System.out.println(json);
		
		Person[] deserializer=mapper.readValue(json, Person[].class);
		System.out.println(Arrays.toString(deserializer));
	
		
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson=builder.create();
		System.out.println(gson.toJson(persons));
		System.out.println(Arrays.toString(gson.fromJson(gson.toJson(persons), Person[].class)));
		}

	}


