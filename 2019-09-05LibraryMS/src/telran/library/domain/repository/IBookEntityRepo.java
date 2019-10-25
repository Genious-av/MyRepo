package telran.library.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.domain.entities.BookEntity;

public interface IBookEntityRepo extends JpaRepository<BookEntity, Long>{
	BookEntity findByBook(BookEntity book);
}
