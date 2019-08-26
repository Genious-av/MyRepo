package application;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dto.Person;

public class MongoTest {
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost",27017); //create connection to Mongo
		MongoDatabase mongoDB=mongoClient.getDatabase("TelRan2019-08-13"); //connection to definite collection
		MongoCollection<Document> personCollection=mongoDB.getCollection("Collection TelRan2019-08-13");
		
		String  dbQuery="{$and: [{weight: {$gte:80}},{age:{$lte:50}}] }";
		for(Document document: personCollection.find(BasicDBObject.parse(dbQuery))) { 
			System.out.println(
				new Person(document.getString("name"),
							document.getInteger("age"),
							document.getDouble("weight"),
							document.getBoolean("married")));
			//System.out.println(document.get("_id"));	
		}
	}
}
