package dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	private long isbn;
	private String title;
	private double price;
	private Set<Author> authors;
	
	
	
	public Book(long isbn, String title, double price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}
	
	
	
	
}


