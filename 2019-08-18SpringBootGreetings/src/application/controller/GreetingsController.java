package application.controller;

import java.util.Base64;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.Person;

@RestController

@RequestMapping("/greet") // all request should start with /greet, other requests wiil not work - request annotation for all request
public class GreetingsController {
	
	@GetMapping("/sasha") // sasha after /greet - getMapping - used method MAP
	public String sasha() {
		return "Go To hell!!";
	}
	
	@GetMapping("/masha")
	public String masha() {
		return "Glad to see you";
	}
	
	@PostMapping("/goodMorning")
	public String goodMorning(@RequestBody Person person) { //@Requestbody - takes data from request body
		return "Good Morning, "+ person.getFirstName()+" "+ person.getLastName()+"!";
	}
	
	@GetMapping("/fullname") //http://localhost:8080/greet/fullname?fname=Alex&lname=Pupkin
	public String  fullName(@RequestParam("fname")   String firstName,@RequestParam("lname")  String lastName) {// @RequestParamt takes data from request after?
		return firstName+" "+lastName;
	}
	
	@GetMapping("/auth")
	public String getAuth(@RequestHeader("Authorization") String auth) {
		System.out.println(auth);
		String decoded=new String(Base64.getDecoder().decode(auth.substring(auth.indexOf(' ')+1))); //getting String from byte[]
		System.out.println(decoded);
		return "ok";
	}
	
	@GetMapping("/name/{id}") //in{}  contains variable which is put to getName(int id)
	public String getName(@PathVariable("id")int id) {
		String[] names= {"Sasha","Masha","Dasha","Lesha","Rasha"};
		return id>=0 && id<5? names[id]:"wrong id";
	}
	
	@GetMapping("calc")
	public String  calc(@RequestParam("first")   float firstNum,@RequestParam("second")  float secondNum ,@RequestParam("oper")int oper) {
		Float res=0f;
		String resultCalc="";
		switch(oper) {
			case 0: res=firstNum+secondNum; break;
			case 1: res=firstNum-secondNum; break;
			case 2: res=firstNum*secondNum; break;
			case 3: if(secondNum==0) {
				return resultCalc="ZeroError";
				
			}	else {
				res=firstNum/secondNum; break;
			}
			default: return resultCalc="Error operation"; 
		}
		resultCalc=res.toString();
		return resultCalc;
	}
	
}
