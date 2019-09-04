package dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Book {
	private long isbn;
	private String title;
	private Set<Author> authors=new HashSet<Author>();
	
}
