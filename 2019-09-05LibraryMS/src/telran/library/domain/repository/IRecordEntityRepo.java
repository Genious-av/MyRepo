package telran.library.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.domain.entities.BookEntity;
import telran.library.domain.entities.RecordEntity;
import telran.library.dto.Book;

public interface IRecordEntityRepo extends JpaRepository<RecordEntity, Integer> {

	List<RecordEntity> findByBook(BookEntity book);
}
