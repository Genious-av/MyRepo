package telran.library.service.implementation;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.library.domain.entities.AuthorEntity;
import telran.library.domain.entities.BookEntity;
import telran.library.domain.entities.PublisherEntity;
import telran.library.domain.entities.ReaderEntity;
import telran.library.domain.entities.RecordEntity;
import telran.library.domain.repository.IAuthorEntityRepo;
import telran.library.domain.repository.IBookEntityRepo;
import telran.library.domain.repository.IPublicherEntityRepo;
import telran.library.domain.repository.IReaderEntityRepo;
import telran.library.domain.repository.IRecordEntityRepo;
import telran.library.dto.Book;
import telran.library.dto.LibReturnCode;
import telran.library.dto.PublisherAuthor;
import telran.library.dto.Reader;
import telran.library.dto.ReaderBookDelay;
import telran.library.dto.Record;
import telran.library.service.interfaces.ILibrary;
@Service
public class LibraryService implements ILibrary {

	
	@Autowired
	IAuthorEntityRepo authorRepo;
	
	
	@Autowired
	IBookEntityRepo bookRepo;
	
	
	@Autowired
	IPublicherEntityRepo publisherRepo;
	
	
	@Autowired
	IReaderEntityRepo readerRepo;
	
	@Autowired
	IRecordEntityRepo recordRepo;
	
	
	
	@Transactional
	@Override
	public LibReturnCode addBookItem(Book book) {
		 if (bookRepo.existsById(book.getIsbn()))
	            return LibReturnCode.BOOK_ALREADY_EXISTS;

	        PublisherEntity publisherEntity = publisherRepo.findById(book.getPublisherName()).orElse(null);
	        if (Objects.isNull(publisherEntity))
	            return LibReturnCode.PUBLISHER_NOT_EXISTS;

	        List<AuthorEntity> authors = authorRepo.findAllById(book.getAuthors());
	        if (authors.size()<book.getAuthors().size()) return LibReturnCode.AUTHOR_NOT_EXISTS;
	        
	        BookEntity bookEntity = new BookEntity(book, new HashSet<>(authors), publisherEntity);

	        bookRepo.save(bookEntity);
		return LibReturnCode.OK;
	}

	@Override
	public LibReturnCode addBookExemplar(long isbn, int amount) {
		if(!bookRepo.existsById(isbn)) return LibReturnCode.BOOK_NOT_EXISTS;
		BookEntity book=bookRepo.findById(isbn).orElse(null);
		book.setAmountInLibrary(book.getAmountInLibrary()+amount);
		return LibReturnCode.OK;
	}

	@Override
	public Book getBookItem(long isbn) {
		if(!bookRepo.existsById(isbn)) return null;
	return bookRepo.findById(isbn).orElse(null).getBook();
	}

	@Override
	public LibReturnCode moveToArchive(long isbn) {
		if(!bookRepo.existsById(isbn)) return LibReturnCode.BOOK_NOT_EXISTS;
		if(getBookFreeAmount(isbn)==0)  return LibReturnCode.BOOK_IN_USE;
		BookEntity book=bookRepo.findById(isbn).orElse(null);
		book.setArchivingDate(LocalDate.now());
		bookRepo.save(book);
		return LibReturnCode.OK;
	}

	@Override
	public LibReturnCode removeExemplar(long isbn) {
		if(!bookRepo.existsById(isbn)) return LibReturnCode.BOOK_NOT_EXISTS;
		if(getBookFreeAmount(isbn)==0)  return LibReturnCode.BOOK_IN_USE;
		BookEntity book=bookRepo.findById(isbn).orElse(null);
		book.setAmountInLibrary(book.getAmountInLibrary()-1);
		bookRepo.save(book);
	 return LibReturnCode.OK;
	}

	@Override
	public LibReturnCode lostExemplar(long isbn, long readerId) {
		if(!bookRepo.existsById(isbn)) return LibReturnCode.BOOK_NOT_EXISTS;
		if(!readerRepo.existsById(readerId)) return LibReturnCode.READER_NOT_EXISTS;
		returnBook(isbn, readerId, LocalDate.now());
		removeExemplar(isbn);
		return LibReturnCode.OK;
	}
	
	@Transactional
	@Override
	public LibReturnCode addReader(Reader reader) {
		if(readerRepo.existsById(reader.getId())) return LibReturnCode.READER_ALREADY_EXISTS;
		ReaderEntity readerE=new ReaderEntity(reader.getId(), reader.getFullName(), reader.getPhone(), reader.getEmail(), reader.getAddress(), reader.getBirthDate());
		readerRepo.save(readerE);
		return LibReturnCode.OK;
	}

	@Override
	public Reader getReader(long readerId) {
	if(!readerRepo.existsById(readerId)) return null;
	ReaderEntity readerE=readerRepo.findById(readerId).orElse(null);
	
		return new Reader(readerE.getId(), readerE.getFullName(), readerE.getPhone(), readerE.getEmail(), readerE.getAddress(), readerE.getBirthDate());
	}
	@Transactional
	@Override
	public LibReturnCode updateReaderEmail(long readerId, String email) {
		if(!readerRepo.existsById(readerId)) return LibReturnCode.READER_NOT_EXISTS;
		ReaderEntity readerE=readerRepo.findById(readerId).orElse(null);
		readerE.setEmail(email);
		readerRepo.save(readerE);
		return LibReturnCode.OK;
	}
	@Transactional
	@Override
	public LibReturnCode updateReaderPhone(long readerId, String phone) {
		if(!readerRepo.existsById(readerId)) return LibReturnCode.READER_NOT_EXISTS;
		ReaderEntity readerE=readerRepo.findById(readerId).orElse(null);
		readerE.setPhone(phone);
		readerRepo.save(readerE);
		return LibReturnCode.OK;
	}
	@Transactional
	@Override
	public LibReturnCode updateReaderAddress(long readerId, String address) {
		if(!readerRepo.existsById(readerId)) return LibReturnCode.READER_NOT_EXISTS;
		ReaderEntity readerE=readerRepo.findById(readerId).orElse(null);
		readerE.setAddress(address);
		readerRepo.save(readerE);
		return LibReturnCode.OK;
	}

	@Override
	public LibReturnCode addPublisher(PublisherAuthor publisher) {
		if(publisherRepo.existsById(publisher.getName())) return LibReturnCode.PUBLISHER_ALREADY_EXISTS;
		
		PublisherEntity publisherE=new PublisherEntity(publisher.getName(), publisher.getCountry());
		publisherRepo.save(publisherE);
		return LibReturnCode.OK;
	}

	@Override
	public PublisherAuthor getPublisherByName(String publisherName) {
		if(!publisherRepo.existsById(publisherName)) return null;
		PublisherEntity publisherE=publisherRepo.findById(publisherName).orElse(null);
	return new PublisherAuthor(publisherE.getName(), publisherE.getCountry());
	}

	@Override
	public List<PublisherAuthor> getPublishersByCountry(String country) {
		List<PublisherEntity> publisherE=publisherRepo.findByCountry(country);
		
		return publisherE.stream()
								.map(pub->pub.getPublisherAuthor())
								.collect(Collectors.toList());
		
	}

	@Override
	public PublisherAuthor getPublisherByBook(long isbn) {
		if(!bookRepo.existsById(isbn)) return null;
		BookEntity bookE=bookRepo.findById(isbn).orElse(null);
		return bookE.getPublisher().getPublisherAuthor();
	}

	@Override
	public LibReturnCode addAuthor(PublisherAuthor author) {
		if(authorRepo.existsById(author.getName())) return LibReturnCode.AUTHOR_ALREADY_EXISTS;
		AuthorEntity authorE=new AuthorEntity(author.getName(), author.getCountry());
		authorRepo.save(authorE);
		return LibReturnCode.OK;
	}

	@Override
	public List<PublisherAuthor> getAuthorsByName(String name) {
		
	return null;
	}

	@Override
	public List<PublisherAuthor> getAuthorsByCountry(String country) {
		
		return authorRepo.findByCountry(country).stream()
												.map(au->au.getPublisherAuthor())
												.collect(Collectors.toList());
	}

	@Override
	public List<PublisherAuthor> getAuthorsByBook(long isbn) {
		if(!bookRepo.existsById(isbn)) return null;
		return bookRepo.findById(isbn).orElse(null).getAuthors().stream().map(au->au.getPublisherAuthor()).collect(Collectors.toList());
	}

	@Override
	public LibReturnCode pickupBook(long isbn, long readerId, LocalDate pickupDate) {
		if(!bookRepo.existsById(isbn)) return LibReturnCode.BOOK_NOT_EXISTS;
		if(!readerRepo.existsById(readerId)) return LibReturnCode.READER_NOT_EXISTS;
	
		BookEntity bookE=bookRepo.findById(isbn).orElse(null);
		if(bookE.getAmountInLibrary()<=getBookFreeAmount(isbn)) return LibReturnCode.BOOK_IN_USE;
		
		ReaderEntity reader=readerRepo.findById(readerId).orElse(null);
		RecordEntity record = new RecordEntity(pickupDate, bookE,reader);
		recordRepo.save(record);
		
		return LibReturnCode.OK;
	}

	@Override
	public LibReturnCode returnBook(long isbn, long readerId, LocalDate returnDate) {
		if(!bookRepo.existsById(isbn)) return LibReturnCode.BOOK_NOT_EXISTS;
		if(!readerRepo.existsById(readerId)) return LibReturnCode.READER_NOT_EXISTS;
		
		
		RecordEntity record=recordRepo.findByBook(bookRepo.findById(isbn).orElse(null)).stream().filter(rec->rec.getDateOfReturning()==null).collect(Collectors.toList()).get(0);
		record.setDateOfReturning(returnDate);
		recordRepo.save(record);
		
		
		return LibReturnCode.OK;
	}

	@Override
	public List<Record> findRecordsByBook(long isbn, LocalDate from, LocalDate to) {
		if(!bookRepo.existsById(isbn)) return null;
		List<RecordEntity> recordsE=recordRepo.findByBook(bookRepo.findById(isbn).orElse(null));
		
		;
		return null;
	}

	@Override
	public List<Record> findRecordsByReader(long readerId, LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> findRecordsByReturnDate(LocalDate returnDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> findOpenRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getMostPopularBooks(LocalDate from_date, LocalDate to_date, int from_age, int to_age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PublisherAuthor> getMostPopularAuthors(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reader> getMostActiveReaders(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reader> getMostDelayingReaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksNotPickedUp(int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExistBookInArchive(long isbn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> getDelayedBooksByReader(long readerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReaderBookDelay> getReadersDelayingBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public	int getBookFreeAmount(long isbn ) {
		if(!bookRepo.existsById(isbn)) return 0;
	return recordRepo.findByBook(bookRepo.findById(isbn).orElse(null)).stream()
				.filter(record->record.getDateOfReturning()==null)
				.collect(Collectors.toList()).size();
		
	}
	
	
	
	
	
}
