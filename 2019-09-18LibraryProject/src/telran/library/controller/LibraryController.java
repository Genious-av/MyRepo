package telran.library.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(value=LibraryApiConstants.ADD_AUTHOR)
	LibReturnCode addAuthor(@RequestBody PublisherAuthor author) {
		return library.addAuthor(author);
	}
	
	@PostMapping(value=LibraryApiConstants.ADD_BOOK)
	LibReturnCode addBook(@RequestBody Book book) {
		return library.addBookItem(book);
	}
	
	@PostMapping(value=LibraryApiConstants.ADD_PUBLISHER)
	LibReturnCode addPublisher(@RequestBody PublisherAuthor publisher) {
		return library.addPublisher(publisher);
	}
	
	
	@GetMapping(value=LibraryApiConstants.GET_AUTHOR)
	List<PublisherAuthor> getAuthor(@RequestParam String name) {
		return library.getAuthorsByName(name);
	}
	
	@GetMapping(value=LibraryApiConstants.GET_BOOK)
	Book getBook(@RequestParam long ISBN) {
		return library.getBookItem(ISBN);
	}
	
	@GetMapping(value=LibraryApiConstants.GET_PUBLISHER)
	PublisherAuthor getPublisher(@RequestParam String name) {
		return library.getPublisherByName(name);
	}
	
	@PostMapping(value=LibraryApiConstants.PICK_BOOK)
	LibReturnCode pickupBook(@RequestBody PickReturnData data) {
		return library.pickupBook(data.getIsbn(), data.getId(), data.getDate());
	}
	
	@PostMapping(value=LibraryApiConstants.RETURN_BOOK)
	LibReturnCode returnBook(@RequestBody PickReturnData data) {
		return library.returnBook(data.getIsbn(), data.getId(), data.getDate());
	}
	
	@GetMapping(value=LibraryApiConstants.ACTIVE_READER)
	List<Reader> getActiveReader( @RequestParam LocalDate from, @RequestParam LocalDate to) {
		return library.getMostActiveReaders(from, to);
	}
	
	
	
}
