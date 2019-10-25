package telran.library.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.domain.entities.BookEntity;
import telran.library.domain.entities.ReaderEntity;
import telran.library.domain.entities.RecordEntity;

public interface RecordRepository extends JpaRepository<RecordEntity, Integer> {

	int countByBookAndDateOfReturningNull(BookEntity book);
	boolean existsByBookAndDateOfReturningIsNullAndReader(BookEntity book, ReaderEntity reader);
	List<RecordEntity> findByBook(BookEntity book);
	List<RecordEntity> findByBookAndDatePickingingUpBetween(BookEntity book, LocalDate from, LocalDate to);
	List<RecordEntity> findByReaderAndDatePickingingUpBetween(ReaderEntity reader, LocalDate from, LocalDate to);
	List<RecordEntity> findByDateOfReturning(LocalDate returnDate);
	List<RecordEntity> findByDateOfReturningIsNull();
	
}
