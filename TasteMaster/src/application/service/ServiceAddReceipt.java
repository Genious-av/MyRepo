package application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.ReceiptEntity;
import application.repo.IReceiptEntityRepo;
import dto.ReceiptDTO;
@Service
public class ServiceAddReceipt implements IServiceADDReceipt {
	@Autowired
	IReceiptEntityRepo receiptRepo;
	
	@Override
	public List<ReceiptDTO>  getAllDishes() {
	System.out.println(receiptRepo.findAll().stream().map(receipt->receipt.receiptToDto()).collect(Collectors.toList()));
		return receiptRepo.findAll().stream().map(receipt->receipt.receiptToDto()).collect(Collectors.toList());
	}

	@Override
	public Boolean addReceipt(ReceiptDTO receipt) {
		System.out.println(receipt.getNameOfDish());
		System.out.println(receipt.getTypeOfDish());
		receiptRepo.save(new ReceiptEntity(receipt.getNameOfDish(), receipt.getTypeOfDish(), receipt.getTimeofDish(),receipt.getIngridient(),receipt.getVolumeofIngridients(),receipt.getTypeOfMesure(),receipt.getCommentsToCook()));
		return true;
	}

	@Override
	public List<String> getAllNamesOfDishes() {
		
		return receiptRepo.findAll().stream().map(receipt->receipt.getNameOfDish()).collect(Collectors.toList());
	}

}
