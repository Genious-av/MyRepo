package application.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import application.entity.AuthorEntity;
import application.entity.BookEntity;
import dto.Author;
import dto.Book;

public interface IBookAuthorService {

	boolean addAuthor(String authorName, int authorAge);
	public boolean addBook(long isbn,String title, double price);
	public boolean setAuthor(long isbn, String authorName);
	public boolean setAuthors(long isbn, Set<String> authorSet);
	public List<Author> getAllAuthors();
	public HashSet<Author> getAuthorsByBook(long isbn);
	public Set<Book> getBooksbyAuthor(String authorName);
}