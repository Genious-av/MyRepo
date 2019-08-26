package application.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.AuthorEntity;
import application.entity.BookEntity;
import application.repo.AuthorJPARepo;
import application.repo.BookJPARepo;
import dto.Author;
import dto.Book;

@Service
public class BookAuthorService implements IBookAuthorService {

	@Autowired
	AuthorJPARepo authorRepo;
	
	@Autowired
	BookJPARepo bookRepo;
	
	
	@Override
	public boolean addAuthor(String authorName, int authorAge) {
		
		authorRepo.save(new AuthorEntity(authorName,authorAge));
	return true;
	}
	
	@Override
	public boolean addBook(long isbn, String title, double price) {
		if(bookRepo.existsById(isbn)) return false;
		bookRepo.save(new BookEntity(isbn,title, price));
		return true;
	}
	
	@Override
	public boolean setAuthor(long isbn, String authorName) {
		
		BookEntity book=bookRepo.getOne(isbn);
		if(book.getAuthors().add(authorRepo.getOne(authorName))){
			book.getAuthors().add(authorRepo.getOne(authorName));
			bookRepo.save(book);
		}
		
		return true;
		
	}
	
	@Override
	public boolean setAuthors(long isbn, Set<String> authorSet) {
		
		BookEntity book = bookRepo.getOne(isbn);
		Set<AuthorEntity> bookAuthors = book.getAuthors();
		for(String authorName: authorSet ) {
			if(authorRepo.existsById(authorName)) bookAuthors.add(authorRepo.getOne(authorName));
		}
		
		bookRepo.save(book);
		return true;
	}
	
	@Override
	public List<Author> getAllAuthors(){
		BookEntity bookE=new BookEntity();
		List<Author> authors = new ArrayList<Author>();
		for(AuthorEntity authorE: authorRepo.findAll()) {
			authors.add(authorE.getAuthor());
		}
		return authors;
	}
	
	
	@Override
	public HashSet<Author> getAuthorsByBook(long isbn){
		BookEntity book=bookRepo.findById(isbn).orElse(null);
		HashSet<Author>result = new HashSet<Author>();
		for(AuthorEntity authorsE: book.getAuthors()) {
			result.add(authorsE.getAuthor());
		}
		return book == null? new HashSet<Author>():result;
	
	}
	
	
	@Override
	public Set<Book> getBooksbyAuthor(String authorName){
		AuthorEntity author =authorRepo.findById(authorName).orElse(null);
		HashSet<Book> result=new HashSet<Book>();
		
		for(BookEntity bookE: author.getBooks()) {
			result.add(bookE.getBook());
		}
		
		return author ==null? new HashSet<Book>(): result;
	}

	
	
	

	
	
}
