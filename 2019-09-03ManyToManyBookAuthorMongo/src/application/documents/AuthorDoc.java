package application.documents;

import java.util.HashSet;
import java.util.Set;

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
@EqualsAndHashCode(exclude="books")

@Document(collection="author")
public class AuthorDoc {
	
	@Id
	private int id;
	private String name;
	private Set<Long> books=new HashSet<Long>();
	
	
	public AuthorDoc(Author author) {
		this.id=author.getId();
		this.name=author.getName();
	}
	
	public Author getAuthor() {
		return new Author(id,name);
	}
	
	
}
