package application.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import dto.Author;
import dto.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data


@Entity
@Table(name="author")
public class AuthorEntity {
	
	@Id
	@Column(length=100)
	private String name;
	private int age;
	@ManyToMany(mappedBy="authors",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<BookEntity> books = new HashSet<>();

	
	public Set<Book> getSetBook(Set<BookEntity> booksE){
		Set<Book> books = new HashSet<Book>();
		for(BookEntity bookE: booksE) {
			books.add(bookE.getBook());
		}
	return books;
	}
	

	public Set<BookEntity> getSetBookEntity(Set<Book> books){
		Set<BookEntity> booksE = new HashSet<BookEntity>();
		for(Book book: books) {
			booksE.add(new BookEntity(book));
		}
		return booksE;
		
	}
	
	
	public AuthorEntity(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	
	
	public Author getAuthor() {
		return new Author(name,age);
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorEntity other = (AuthorEntity) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



		
}
