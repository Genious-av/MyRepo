package telran.aop.logs.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogsTestController {
	@PostMapping("/add")
	String addSomething() {
		if(Math.random()*100>50) {
			throw new RuntimeException("Random gen number is more then 50");
			}
		return "added";
	}
	
	@PutMapping("/update")
	void updateSomething() {
	
	}
	
	@GetMapping("greeting")
	String greeting() {
		return "hello,world!";
				
	}
	
	@DeleteMapping("/remove")
	boolean deleteSomething() {
		return true;
	}
}
