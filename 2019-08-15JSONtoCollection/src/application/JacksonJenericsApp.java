package application;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import dto.JsonEntity;
import dto.Person;

public class JacksonJenericsApp {

	public static void main(String[] args) throws IOException {
	ArrayList<Person> persons=new ArrayList<>();
	persons.add(new Person("Sasha", LocalDate.of(1988, 02, 13)));
	persons.add(new Person("Masha", LocalDate.of(1989, 03, 06)));

	ObjectMapper mapper = new ObjectMapper();
	mapper.registerModule(new JavaTimeModule());
	String json=mapper.writeValueAsString(persons); //persons to JSON
	
	String collection = mapper.getTypeFactory().constructCollectionType(List.class, Person.class).toCanonical(); //get name of CollectionType
	
	JsonEntity jSonEntity = new JsonEntity(json,collection); //convert JSON to type JsoneEntity using collection - name of CollectionType
	String jsonPack=mapper.writeValueAsString(jSonEntity);
	System.out.println(jsonPack);
	
	JsonEntity jsonEntityReceived = mapper.readValue(jsonPack, JsonEntity.class); // get data of type JsonEntity
	
	ArrayList<Person> received = mapper.readValue(jsonEntityReceived.getJson(), // read json of type JSON ENTITY using name of collection
			mapper.getTypeFactory().constructFromCanonical(jsonEntityReceived.getCollectionName()));
	System.out.println(received.get(0).getBithDate());
	
	}

}
