package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.service.IServiceADDReceipt;
import dto.ReceiptDTO;

@RestController
public class AddReceiptController {
	
	
	@Autowired
	IServiceADDReceipt serviceAddRec;
	

	@PostMapping("/addReceipt")
	public boolean addReceipt(@RequestBody ReceiptDTO receipt ) {
		
		serviceAddRec.addReceipt(receipt);
		
		return true;
	}
	@GetMapping("/getDishesName")
	public List<String> getAllNamesOfDishes() {
		return serviceAddRec.getAllNamesOfDishes();
	}
	
	@GetMapping("/getAllDishes")
	public List<ReceiptDTO> getAllDishes() {
		return serviceAddRec.getAllDishes();
	}
	
	
}
