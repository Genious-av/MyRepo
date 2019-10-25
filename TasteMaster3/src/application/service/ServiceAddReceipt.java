package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.ReceiptDTO;
@Service
public class ServiceAddReceipt implements IServiceADDReceipt {
	@Autowired
	IReceiptEntityRepo receiptRepo;
	
	@Override
	public String getAllDishes() {
		
		return null;
	}

	@Override
	public Boolean addReceipt(ReceiptDTO receipt) {
		receiptRepo.sa
		return null;
	}

}
