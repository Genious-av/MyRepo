package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.service.IServiceADDReceipt;

@RestController
public class AddReceiptController {
	
	@Autowired
	IServiceADDReceipt serviceAddRec;
	
	@PostMapping("/addReceipt")
	public boolean addReceipt(@RequestBody ReceiptEntity receipt) {
		serviceAddRec.
		
		return true;
	}
	
}
