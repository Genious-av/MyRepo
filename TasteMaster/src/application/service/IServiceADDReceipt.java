package application.service;

import java.util.List;

import dto.ReceiptDTO;

public interface IServiceADDReceipt {
	Boolean addReceipt(ReceiptDTO receipt);
	List<ReceiptDTO> getAllDishes();
	List<String> getAllNamesOfDishes();
}