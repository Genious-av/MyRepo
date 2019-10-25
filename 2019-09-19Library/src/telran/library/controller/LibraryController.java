package telran.library.controller;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import telran.library.api.*;
import telran.library.domain.entities.AuthorEntity;
import telran.library.dto.*;
import telran.library.service.interfaces.ILibrary;

@RestController
public class LibraryController {
    @Autowired
    ILibrary library;

  @PostConstruct
  public void postConstructMethod() {
	  System.out.println("post construct method");
  }
  
  @PreDestroy
  public void predestroyMethod() { //last method which executes before closing of application context
	  System.out.println("predestroy method"); 
  }
    
    @PutMapping(value = LibraryApiConstants.ADD_BOOK_EXEMPLAR)
    public LibReturnCode addBookExemplar(@RequestParam long isbn, @RequestParam int amount) {
        return library.addBookExemplar(isbn, amount);
    }

    @PostMapping(value = LibraryApiConstants.ADD_BOOK)
    public LibReturnCode addBookItem(@RequestBody Book book) {
        return library.addBookItem(book);
    }

    @GetMapping(value = LibraryApiConstants.GET_BOOK)
    public Book getBookItem(@RequestParam Long isbn) {
        return library.getBookItem(isbn);
    }

    @DeleteMapping(value = LibraryApiConstants.MOVE_TO_ARCHIVE)
    public LibReturnCode moveToArchive(@RequestParam long isbn) {
        return library.moveToArchive(isbn);
    }

    @DeleteMapping(value = LibraryApiConstants.REMOVE_BOOK)
    public LibReturnCode removeExemplar(@RequestParam long isbn) {
        return library.removeExemplar(isbn);
    }

    @DeleteMapping(value = LibraryApiConstants.LOST_BOOK)
    public LibReturnCode lostExemplar(@RequestParam long isbn, @RequestParam long readerId) {
        return library.lostExemplar(isbn, readerId);
    }

    @PostMapping(value = LibraryApiConstants.ADD_READER)
    public LibReturnCode addReader(@RequestBody Reader reader) {
        return library.addReader(reader);
    }

    @GetMapping(value = LibraryApiConstants.GET_READER)
    public Reader getReader(@RequestParam long readerId) {
        return library.getReader(readerId);
    }

    @PutMapping(value = LibraryApiConstants.EMAIL_UPDATE)
    public LibReturnCode updateReaderEmail(@RequestParam long readerId, @RequestParam String email) {
        return library.updateReaderEmail(readerId, email);
    }

    @PutMapping(value = LibraryApiConstants.PHONE_UPDATE)
    public LibReturnCode updateReaderPhone(@RequestParam long readerId, @RequestParam String phone) {
        return library.updateReaderPhone(readerId, phone);
    }

    @PutMapping(value = LibraryApiConstants.ADDRESS_UPDATE)
    public LibReturnCode updateReaderAddress(@RequestParam long readerId, @RequestParam String address) {
        return library.updateReaderAddress(readerId, address);
    }

    @PostMapping(value = LibraryApiConstants.ADD_PUBLISHER)
    public LibReturnCode addPublisher(@RequestBody PublisherAuthor publisher) {
        return library.addPublisher(publisher);
    }

    @GetMapping(value = LibraryApiConstants.GET_PUBLISHER)
    PublisherAuthor getPublisherByName(@RequestParam String publisherName) {
        return library.getPublisherByName(publisherName);
    }

    @GetMapping(value = LibraryApiConstants.GET_PUBLISHER_COUNTRY)
    List<PublisherAuthor> getPublisherByCountry(@RequestParam String country) {
        return library.getAuthorsByCountry(country);
    }

    @GetMapping(value = LibraryApiConstants.GET_PUBLISHER_BOOK)
    public PublisherAuthor getPublisherByBook(@RequestParam long isbn) {
        return library.getPublisherByBook(isbn);
    }

    @PostMapping(value = LibraryApiConstants.ADD_AUTHOR)
    public LibReturnCode addAuthor(@RequestBody PublisherAuthor author) {
        return library.addAuthor(author);
    }

    @GetMapping(value = LibraryApiConstants.GET_AUTHORS)
    List<PublisherAuthor> getAuthorsByName(@RequestParam String name) {
        return library.getAuthorsByName(name);
    }

    @GetMapping(value = LibraryApiConstants.GET_AUTHORS_COUNTRY)
    List<PublisherAuthor> getAuthorsByCountry(@RequestParam String country) {
        return library.getAuthorsByCountry(country);
    }

    @GetMapping(value = LibraryApiConstants.GET_AUTHORS_BOOK)
    List<PublisherAuthor> getAuthorsByBook(@RequestParam long isbn) {
        return library.getAuthorsByBook(isbn);
    }

    @PostMapping(value = LibraryApiConstants.PICK_BOOK)
    public LibReturnCode pickupBook(@RequestBody PickReturnData data){
        return library.pickupBook(data.isbn, data.id, data.date);
    }

    @PostMapping(value = LibraryApiConstants.RETURN_BOOK)
    public LibReturnCode returnBook(@RequestBody PickReturnData data) {
        return library.returnBook(data.isbn, data.id, data.date);
    }

    @GetMapping(value = LibraryApiConstants.RECORDS_BOOK)
    public List<Record> findRecordsByBook(@RequestParam long isbn,
                                          @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                          @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to){
        return library.findRecordsByBook(isbn, from, to);
    }

    @GetMapping(value = LibraryApiConstants.RECORDS_READER)
    public List<Record> findRecordsByReader(@RequestParam long readerId,
                                          @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                          @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to){
        return library.findRecordsByReader(readerId, from, to);
    }

    @GetMapping(value = LibraryApiConstants.ACTIVE_READER)
    public List<Reader> getMostActiveReaders(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                             @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return library.getMostActiveReaders(from, to);
    }
}


