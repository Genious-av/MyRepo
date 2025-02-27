package telran.library.domain.entities;

import lombok.*;
import telran.library.dto.PublisherAuthor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString
@Getter

@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    String name;
    String country;
    
    
    public PublisherAuthor getPublisherAuthor() {
    	return new PublisherAuthor(this.getName(), this.getCountry());
    }
}

