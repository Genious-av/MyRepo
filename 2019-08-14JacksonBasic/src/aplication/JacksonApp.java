package aplication;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import dto.Address;
import dto.Contact;
import dto.Person;

public class JacksonApp {
	public static void main(String[] args) throws IOException {
		Contact contact1=new Contact("Masha", "322-322");
		Contact contact2=new Contact("Dasha",  "566-677");
		Contact[] contacts= {contact1,contact2};
		Address address1= new Address("Haifa", "Gistadrut", 10, 12);
		Person person1 = new Person("David", "Shats", address1, contacts, LocalDate.of(1987, 12, 12));
		//System.out.println(person1);
		
		ObjectMapper mapper = new ObjectMapper(); //
		
		mapper.registerModule(new JavaTimeModule()); //used to  conver data in format LOCALDATE appropriately
		String json=mapper.writeValueAsString(person1);
		GsonBuilder builder = new GsonBuilder();
		Gson gson=builder.create();
		System.out.println(gson.toJson(person1));
		//System.out.println(json);
		System.out.println(gson.fromJson(gson.toJson(person1), Person.class));
		Person deserialized=mapper.readValue(json, Person.class);
		//System.out.println(deserialized);
	}
	
	
}
