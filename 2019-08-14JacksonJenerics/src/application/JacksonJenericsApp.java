package application;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;



import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import dto.Person;

public class JacksonJenericsApp {

	public static void main(String[] args) throws IOException {
	ArrayList<Person> persons=new ArrayList<>();
	persons.add(new Person("Sasha", LocalDate.of(1988, 02, 13)));
	persons.add(new Person("Masha", LocalDate.of(1989, 03, 06)));

	
	
		
	//ObjectWriter objectWriter=mapper.writerFor(new TypeReference<ArrayList<Person>>() {});//create instance of anonimous abstract class TypeReference, parametrized with ArrayList<Person>
	//used because JACKSON need to parse ArrayList<PERSON> - possible through class reflection determine parameters of class-parent
	
	
	/* GSON VERSION
	GsonBuilder builder = new GsonBuilder();
	Gson gson=builder.create();
	String gson1=gson.toJson(persons);
	System.out.println(gson1);
	Type ALTypenew = new TypeToken<List<Person>>(){}.getType();
	ArrayList<Person> personsFromGSON = new Gson().fromJson(gson1, ALTypenew);
	System.out.println(personsFromGSON.get(1));
	
	*/
	ObjectMapper mapper = new ObjectMapper();
	mapper.registerModule(new JavaTimeModule()); //used to  convert data in format LOCALDATE appropriately
	
	String json=mapper.writeValueAsString(persons); //write json
	System.out.println(json);

//ArrayList<Person> deserialized= mapper.readValue(json, new TypeReference<ArrayList<Person>>() {} ); // - version 1 - used parent of class to find parametrized class of ArrayList
		
	ArrayList<Person> deserialized = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Person.class)); // - version 2 - create type of parametrized collection through getTypeFactory
	System.out.println(deserialized);
	
	}

}
