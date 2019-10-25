package telran.library.service.interfaces;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.domain.entities.AuthorEntity;
import telran.library.domain.entities.BookEntity;
import telran.library.domain.entities.ReaderEntity;
import telran.library.domain.entities.RecordEntity;

public interface RecordRepository extends JpaRepository<RecordEntity, Integer> {

	int countByBookAndDateOfReturningNull(BookEntity book);
	boolean existsByBookAndDateOfReturningIsNullAndReader(BookEntity book, ReaderEntity reader);
	RecordEntity findByBookIsbnAndDateOfReturningIsNullAndReaderId(long isbn,long id);
	List<RecordEntity> findByBook(BookEntity book);
	List<RecordEntity> findByDateOfReturningIsNull();
	List<RecordEntity> findByDateOfReturning(LocalDate returnDate);
	List<RecordEntity> findByReaderAndDatePickingingUpBetween(ReaderEntity orElse, LocalDate from, LocalDate to);
	List<RecordEntity> findByBookAndDatePickingingUpBetween(BookEntity orElse, LocalDate from, LocalDate to);
}
