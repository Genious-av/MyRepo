package application;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import dto.Person;

public class JavaMongoApp {
	private static final long NUM_PERSON = 30;

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper= new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //if mapper find unknown field - mapper skip them
		MongoClient mongoClient = new MongoClient("localhost",27017); //create connection to Mongo
		
		MongoDatabase mongoDB=mongoClient.getDatabase("TelRan2019-08-15"); //connection to new DB
		try { //used to prevent exception when JAVA tries to create DB with the same name
			mongoDB.createCollection("persons");
		} catch (Exception e) {} //create new collection in database. if database doesn`t exist - creates new DB in string upper
		
		
		
		MongoCollection<Document> persons=mongoDB.getCollection("persons");
		
		//persons.insertOne(Document.parse(mapper.writeValueAsString(Person.randomPerson()))); //insert new document(random person) to DB
	/*
		persons.insertMany(Stream.generate(()->Person.randomPerson()) //insert list of random persons to
							.limit(NUM_PERSON)
							.map(person->{
								try {
									return Document.parse(mapper.writeValueAsString(Person.randomPerson()));
								} catch (JsonProcessingException e) {
									throw new RuntimeException(e); //this runtime is used to prevent JsonProcessingException, without it JAVA ask additionoal return after catch
								}
								
							})
							.collect(Collectors.toList()));
		*/
		String query= "{age: {$gte:50}}"; //filtering all persons older then 50
		
		String query1="{age: {$lt:18}}"; //get age less then 18
		String queryUpdate="{$set: {age:18}}"; //set to age 18
		
		persons.updateMany(BasicDBObject.parse(query1), BasicDBObject.parse(queryUpdate));
		
		/*//FIND return ITERABLE, not result
		String sortArg="{age:-1}"; //sorting -1: descending, 1-ascending
		for(Document document: persons.find().sort(BasicDBObject.parse(sortArg))) {
			System.out.println(mapper.readValue(document.toJson(), Person.class));
		}
		*/
		
		/*
		for(Document document: persons.find(BasicDBObject.parse(query))) { //print all persons
			System.out.println(mapper.readValue(document.toJson(), Person.class));
		};
		
		*/
		
		/* deleting by ID
		String hex="5d552a6879d6d57e59f9e511";
		Iterator<Document> cursor=persons.find(Filters.eq("_id", new ObjectId(hex))).iterator();
		
		Document doc=cursor.hasNext()? cursor.next():null;
		System.out.println(mapper.readValue(doc.toJson(), Person.class));
		*/
		
		//deleting all
//		String queryDelete="{}";
//		persons.deleteMany(BasicDBObject.parse(queryDelete));
		
		String queryDeleteByName="{name : {$eq : name#2}";
				persons.deleteMany(BasicDBObject.parse(queryDeleteByName));
				
				persons.drop();
	}
}
