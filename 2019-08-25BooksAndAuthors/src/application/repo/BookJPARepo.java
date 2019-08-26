package application.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.AuthorEntity;
import application.entity.BookEntity;

public interface BookJPARepo  extends JpaRepository<BookEntity, Long>{
	

}
