package application.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.ReceiptEntity;


public interface IReceiptEntityRepo extends JpaRepository<ReceiptEntity, Integer>{
	
}
