package application.service;

import java.util.Set;

import dto.Author;
import dto.Book;

public interface IMongoMTM {
	void addAuthor(Author author);
	void addBok(Book book);
	
	Book getBook(long isbn);
	Set<Author> getBookAuthors(long isbn);
	Set<Book> getAuthorsBooks(int id);
	
}
