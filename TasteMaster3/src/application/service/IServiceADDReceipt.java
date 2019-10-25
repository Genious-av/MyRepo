package application.service;

import dto.ReceiptDTO;

public interface IServiceADDReceipt {
	Boolean addReceipt(ReceiptDTO receipt);
	String getAllDishes();
}