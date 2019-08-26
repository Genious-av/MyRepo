package application.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.entity.AuthorEntity;
import application.entity.BookEntity;
import application.repo.AuthorJPARepo;
import application.repo.BookJPARepo;
import application.service.IBookAuthorService;
import dto.Author;
import dto.Book;

@RestController
public class BookAuthorController {
	
	@Autowired
	IBookAuthorService service;
	
	@Autowired
	AuthorJPARepo authorRepo;
	
	@Autowired
	BookJPARepo bookRepo;
	
	@PostMapping("/addAuthor")
	public ResponseEntity<String> addAuthor(@RequestParam String authorName, @RequestParam int authorAge) {
		if(authorRepo.existsById(authorName)) return new ResponseEntity<String>("Author exist", HttpStatus.FOUND);
		else if (!authorRepo.existsById(authorName)) {
			service.addAuthor(authorName, authorAge);
			return new ResponseEntity<String>("Author added", HttpStatus.OK);
		}
		return new ResponseEntity<String>("OtherError", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<String> addBook(@RequestParam long  isbn, @RequestParam String title, @RequestParam double price) {
		if(bookRepo.existsById(isbn)) return new ResponseEntity<String>("Book exist", HttpStatus.FOUND);
		else if (!bookRepo.existsById(isbn)) {
			service.addBook(isbn,title, price);
			return new ResponseEntity<String>("Book added", HttpStatus.OK);
		}
		return new ResponseEntity<String>("OtherError", HttpStatus.BAD_REQUEST);
		
	}
	
	
	@PutMapping("/setAuthor")
	
	public ResponseEntity<String> setAuthor(@RequestParam long isbn, @RequestParam String authorName) {
		if(!authorRepo.existsById(authorName)) return  new ResponseEntity<String>("Author doesn`t exist", HttpStatus.NOT_FOUND);
		else if(!bookRepo.existsById(isbn)) return new ResponseEntity<String>("Book doesn`t exist", HttpStatus.NOT_FOUND);
		else if(authorRepo.existsById(authorName) &&bookRepo.existsById(isbn)) {
			service.setAuthor(isbn, authorName);
			return new ResponseEntity<String>("Author setted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("OtherError", HttpStatus.BAD_REQUEST);
	}
	
	
	
	@PutMapping("/setAuthors")
	public ResponseEntity<String> setAuthors(@RequestParam long isbn, @RequestBody Set<String>authorsSet) {
		if(!bookRepo.existsById(isbn)) return new ResponseEntity<String>("Book doesn`t exist", HttpStatus.NOT_FOUND);
		else if(bookRepo.existsById(isbn)) {
			service.setAuthors(isbn, authorsSet);
			return new ResponseEntity<String>("Authors setted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("OtherError", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getAllAuthors")
	public ResponseEntity<List<Author>> getAllAuthors(){
		return new ResponseEntity<List<Author>>(service.getAllAuthors(), HttpStatus.OK);
	}
	
	@GetMapping("/getAuthorsByBook")
	public ResponseEntity<Set<Author>> getAuthorsByBook(@RequestParam long isbn){
	if(!bookRepo.existsById(isbn)) return new ResponseEntity<Set<Author>>(new HashSet<Author>(), HttpStatus.NOT_FOUND);	
	else if(bookRepo.existsById(isbn)) new ResponseEntity<Set<Author>>(service.getAuthorsByBook(isbn), HttpStatus.OK);	 
	return new ResponseEntity<Set<Author>>(new HashSet<Author>(), HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/getBooksByAuthor")
	public ResponseEntity<Set<Book>> getBooksByAuthor(@RequestParam String authorName){
		if(!authorRepo.existsById(authorName)) return  new ResponseEntity<Set<Book>>(new HashSet<Book>(), HttpStatus.NOT_FOUND);	
		else if(authorRepo.existsById(authorName)) return  new ResponseEntity<Set<Book>>(service.getBooksbyAuthor(authorName),HttpStatus.OK);
		return new ResponseEntity<Set<Book>>(new HashSet<Book>(), HttpStatus.BAD_REQUEST);
	}
	
	
}
