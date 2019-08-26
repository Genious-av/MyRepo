package application.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import dto.Author;
import dto.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data


@Entity
@Table(name="book")

public class BookEntity {
	
	@Id
	@Column(length=100)
	private long isbn; 
	private String title;
	private double price;
	

	
	@ManyToMany
	@JsonIgnore
	private Set<AuthorEntity> authors = new HashSet<>();
	
	public Book getBook() {
		Set<Author> authorsDTO=getSetAuthors(authors);

			return new Book(isbn, title, price,authorsDTO );
	}//getSetAuthors(authors)
	
	public BookEntity(Book book) {
		this(book.getIsbn(),book.getTitle(),book.getPrice());
	}
	
	
	public Set<Author> getSetAuthors(Set<AuthorEntity> authorsE){
		Set<Author> authorsDTO=new HashSet<Author>();
		for(AuthorEntity authorE: authorsE) {
			authorsDTO.add(authorE.getAuthor());
		}
		return authorsDTO;
	}
	


	public BookEntity(long isbn, String title, double price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntity other = (BookEntity) obj;
		if (isbn != other.isbn)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (isbn ^ (isbn >>> 32));
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	

	
	
}
