package telran.library.service.impl;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.library.dto.Book;
import telran.library.dto.LibReturnCode;
import telran.library.dto.PublisherAuthor;
import telran.library.dto.Reader;
import telran.library.dto.ReaderBookDelay;
import telran.library.dto.Record;
import telran.library.mappers.Mapper;
import telran.library.service.interfaces.AuthorRepository;
import telran.library.service.interfaces.BookRepository;
import telran.library.service.interfaces.ILibrary;
import telran.library.service.interfaces.PublisherRepository;
import telran.library.service.interfaces.ReaderRepository;
import telran.library.service.interfaces.RecordRepository;
import telran.library.domain.entities.*;

@Service
public class LibraryService implements ILibrary {

	@Autowired
	BookRepository bookRepo;
	@Autowired
	AuthorRepository authorRepo;
	@Autowired
	PublisherRepository publisherRepo;
	@Autowired
	ReaderRepository readerRepo;
	@Autowired
	RecordRepository recordRepo;
	
	@Autowired
	Mapper<BookEntity, Book> bookMapper;
	@Autowired
	Mapper<ReaderEntity, Reader> readerMapper;
	@Autowired
	Mapper<AuthorEntity, PublisherAuthor> authorMapper;
	@Autowired
	Mapper<PublisherEntity, PublisherAuthor> publisherMapper;
	@Autowired
	Mapper<RecordEntity, Record> recordMapper;
	
	
	@Override
	@Transactional
	public LibReturnCode addBookItem(Book book) {
	        if (bookRepo.existsById(book.getIsbn()))
	            return LibReturnCode.BOOK_ALREADY_EXISTS;

	        PublisherEntity publisherEntity = publisherRepo.findById(book.getPublisherName()).orElse(null);
	        if (Objects.isNull(publisherEntity))
	            return LibReturnCode.PUBLISHER_NOT_EXISTS;

	        List<AuthorEntity> authors = authorRepo.findAllById(book.getAuthors());
	        if (authors.size()<book.getAuthors().size())
	            return LibReturnCode.AUTHOR_NOT_EXISTS;
	        bookRepo.save(bookMapper.toEntity(book));
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
        BookEntity book = bookRepo.findById(isbn).orElse(null);
      return bookMapper.toDto(book);
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

	@Override
	public LibReturnCode addReader(Reader reader) {
		if(readerRepo.existsById(reader.getId())) return LibReturnCode.READER_ALREADY_EXISTS;
		ReaderEntity readerE=readerMapper.toEntity(reader);
		readerRepo.save(readerE);
		return LibReturnCode.OK;
	}

	@Override
	public Reader getReader(long readerId) {
		if(!readerRepo.existsById(readerId)) return null;
		ReaderEntity readerE=readerRepo.findById(readerId).orElse(null);
		return readerMapper.toDto(readerE);
	
	}

	@Override
	public LibReturnCode updateReaderEmail(long readerId, String email) {
		if(!readerRepo.existsById(readerId)) return LibReturnCode.READER_NOT_EXISTS;
		ReaderEntity readerE=readerRepo.findById(readerId).orElse(null);
		readerE.setEmail(email);
		readerRepo.save(readerE);
		return LibReturnCode.OK;
	}

	@Override
	public LibReturnCode updateReaderPhone(long readerId, String phone) {
		if(!readerRepo.existsById(readerId)) return LibReturnCode.READER_NOT_EXISTS;
		ReaderEntity readerE=readerRepo.findById(readerId).orElse(null);
		readerE.setPhone(phone);
		readerRepo.save(readerE);
		return LibReturnCode.OK;
	}

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
			
		
		return publisherRepo.findByCountry(country).stream()
													.map(pub->publisherMapper.toDto(pub))
													.collect(Collectors.toList());
	}

	@Override
	public PublisherAuthor getPublisherByBook(long isbn) {
		if(!bookRepo.existsById(isbn)) return null;
		BookEntity bookE=bookRepo.findById(isbn).orElse(null);
		PublisherEntity publisherE=publisherRepo.findById(bookE.getPublisher().getName()).orElse(null);
		return publisherMapper.toDto(publisherE);
	}

	@Override
	public LibReturnCode addAuthor(PublisherAuthor author) {
		if(authorRepo.existsById(author.getName())) return LibReturnCode.AUTHOR_ALREADY_EXISTS;
		AuthorEntity authorE=authorMapper.toEntity(author);
		authorRepo.save(authorE);
		return LibReturnCode.OK;
		
	}

	@Override
	public PublisherAuthor getAuthorsByName(String name) {
		if(!authorRepo.existsById(name)) return null;
	return authorMapper.toDto((authorRepo.findById(name).orElse(null)));
	}

	@Override
	public List<PublisherAuthor> getAuthorsByCountry(String country) {
	return authorRepo.findByCountry(country).stream()
		.map(auth->authorMapper.toDto(auth))
		.collect(Collectors.toList());
	}

	@Override
	public List<PublisherAuthor> getAuthorsByBook(long isbn) {
		if(!bookRepo.existsById(isbn)) return null;
		return bookRepo.findById(isbn).orElse(null).getAuthors().stream().map(auth->authorMapper.toDto(auth)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public LibReturnCode pickupBook(long isbn, long readerId, LocalDate pickupDate) {

		BookEntity book = bookRepo.findById(isbn).orElse(null);
		
		if (Objects.isNull(book)) {
			return LibReturnCode.BOOK_NOT_EXISTS;
		}
		if (recordRepo.countByBookAndDateOfReturningNull(book) == book.getAmountInLibrary()) {
			return LibReturnCode.ALL_EXEMPLARS_IN_USE;
		}
		if (!Objects.isNull(book.getArchivingDate())) {
			return LibReturnCode.BOOK_IN_ARCHIVE;
		}
		
		ReaderEntity reader = readerRepo.findById(readerId).orElse(null);
		
		if (Objects.isNull(reader)) {
			return LibReturnCode.READER_NOT_EXISTS;
		}
		if (recordRepo.existsByBookAndDateOfReturningIsNullAndReader(book, reader)) {
			return LibReturnCode.READER_BOOK_NOT_RETURN;
		}
		recordRepo.save(new RecordEntity(pickupDate, book, reader));
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
		List<RecordEntity> recordsE=recordRepo.findByBookAndDatePickingingUpBetween(bookRepo.findById(isbn).orElse(null), from,to);
		return recordsE.stream().map(rr->recordMapper.toDto(rr)).collect(Collectors.toList());
	}

	@Override
	public List<Record> findRecordsByReader(long readerId, LocalDate from, LocalDate to) {
		if(!readerRepo.existsById(readerId)) return null;
		List<RecordEntity> recordsE=recordRepo.findByReaderAndDatePickingingUpBetween(readerRepo.findById(readerId).orElse(null), from, to);
		return recordsE.stream().map(rr->recordMapper.toDto(rr)).collect(Collectors.toList());
	}

	@Override
	public List<Record> findRecordsByReturnDate(LocalDate returnDate) {
		List<RecordEntity> recordsE=recordRepo.findByDateOfReturning(returnDate);
		return recordsE.stream().map(rr->recordMapper.toDto(rr)).collect(Collectors.toList());
	}

	@Override
	public List<Record> findOpenRecords() {
		List<RecordEntity> recordsE=recordRepo.findByDateOfReturningIsNull();
		return recordsE.stream().map(rr->recordMapper.toDto(rr)).collect(Collectors.toList());
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
		List<ReaderEntity> result=readerRepo.getMostActiveReaders(from,to);
		return result.stream()
							.map(re->readerMapper.toDto(re))
							.collect(Collectors.toList());
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
