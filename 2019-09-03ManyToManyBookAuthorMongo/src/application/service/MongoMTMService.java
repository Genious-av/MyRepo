package application.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.documents.AuthorDoc;
import application.documents.BookDoc;
import application.exceptions.DuplicateDataException;
import application.exceptions.NoDataFoundException;
import application.repo.AuthorMongoRepo;
import application.repo.BookMongoRepo;
import dto.Author;
import dto.Book;
@Service
public class MongoMTMService implements IMongoMTM{

	
	@Autowired
	BookMongoRepo bookRepo;
	
	
	@Autowired
	AuthorMongoRepo authorRepo;
	
	
	
	@Override
	public void addAuthor(Author authorDTO) {
		if(authorRepo.existsById(authorDTO.getId())) throw new DuplicateDataException("Author "+ authorDTO.getId()+" exists");
		authorRepo.save(new AuthorDoc(authorDTO));
	}

	@Override
	public void addBok(Book bookDTO) {
		if(bookRepo.existsById(bookDTO.getIsbn()))  throw new DuplicateDataException("Book "+bookDTO.getIsbn()+" exists");
		for(Author authorDto: bookDTO.getAuthors()) {
			AuthorDoc author=authorRepo.findById(authorDto.getId()).orElse(null);
			if(author==null) {
				author=authorRepo.save(new AuthorDoc(authorDto));
			}
			author.getBooks().add(bookDTO.getIsbn());
			authorRepo.save(author);
		}
		bookRepo.save(new BookDoc(bookDTO));
	}

	@Override
	public Book getBook(long isbn) {
		BookDoc bookDoc=bookRepo.findById(isbn).orElseThrow(()->new NoDataFoundException("No book with isbn: "+ isbn));
		
		return bookDoc.getBook(authorsIdToDoc(bookDoc.getAuthors()));
	}

	@Override
    public Set<Author> getBookAuthors(long isbn) {
		BookDoc bookDoc=bookRepo.findById(isbn).orElseThrow(()->new NoDataFoundException("No book with isbn: "+ isbn));
		return authorsIdToDoc(bookDoc.getAuthors()).stream()
								.map(authorDoc->authorDoc.getAuthor())
								.collect(Collectors.toSet());
		
	}

	@Override
	public Set<Book> getAuthorsBooks(int id) {
		AuthorDoc authorDoc=authorRepo.findById(id).orElseThrow(()->new NoDataFoundException("No author with id: "+ id));
				
		return booksIdToDoc(authorDoc.getBooks()).stream()
												.map(bookDoc->bookDoc.getBook(authorsIdToDoc(bookDoc.getAuthors())))
												.collect(Collectors.toSet());	
				
	}
	
	private Set<AuthorDoc> authorsIdToDoc(Set<Integer> ids){
		Iterable<AuthorDoc> authorDocs=authorRepo.findAllById(ids);
		Set<AuthorDoc> result=new HashSet<>();
		for(AuthorDoc authorDoc:authorDocs) result.add(authorDoc);
		return result;
	}
	
	private Set<BookDoc> booksIdToDoc(Set<Long> ids){
		Iterable<BookDoc> bookDocs=bookRepo.findAllById(ids);
		Set<BookDoc> result=new HashSet<>();
		for(BookDoc bookDoc:bookDocs) result.add(bookDoc);
		return result;
	}
}
