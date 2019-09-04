package application.documents;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dto.Author;
import dto.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude="authors")

@Document(collection="book")
public class BookDoc {
	@Id
	private long isbn;
	private String title;
	private Set<Integer> authors = new HashSet<Integer>();;

	
	public BookDoc(Book book) {
		this.isbn=book.getIsbn();
		this.title=book.getTitle();
		this.authors=book.getAuthors().stream()
										.map(author->author.getId())
										.collect(Collectors.toSet());
	}
	
	public Book getBook(Set<AuthorDoc> authorsDoc) {
		Set<Author> authors=authorsDoc.stream()
										.map(authordoc->authordoc.getAuthor())
										.collect(Collectors.toSet());
		return new Book(isbn,title,authors);
	}
}
