package application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class InterceptorController {
	@GetMapping("/first")
	public String first() {
		return "first";
	}
	
	
	@GetMapping("/second")
	public String second() {
		return "first";
	}
}
