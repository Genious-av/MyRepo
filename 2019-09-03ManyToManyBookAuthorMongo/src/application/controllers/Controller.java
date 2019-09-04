package application.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.service.IMongoMTM;
import dto.Author;
import dto.Book;

@RestController
public class Controller {
	@Autowired
	IMongoMTM service;
	
	@PostMapping("/addAuthor")
	public String addAuthor(@RequestBody Author author) {
		service.addAuthor(author);
		return "ok";
	}
	
	@PostMapping("/addBook")
	public String addBook(@RequestBody Book book) {
		service.addBok(book);
		return "ok";
	}
	
	@GetMapping("/getBook")
	public Book getBook(@RequestParam long isbn) {
		return service.getBook(isbn);
	}
	
	@GetMapping("/getAuthorBooks")
	public Set<Author> getAuthorBooks(@RequestParam long isbn) {
		return service.getBookAuthors(isbn);
	}
	
	
	@GetMapping("/getBooksofAuthor")
	public Set<Book> getBook(@RequestParam int id) {
		return service.getAuthorsBooks(id);
	}
	
	
}
