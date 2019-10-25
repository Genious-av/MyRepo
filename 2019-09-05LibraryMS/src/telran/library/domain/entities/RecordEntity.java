package telran.library.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@EqualsAndHashCode(of = "recordId")
@ToString(exclude = {"book", "reader"})
@Getter

@Entity
@Table(name = "records", indexes= {@Index(columnList="date_of_returning"),@Index(columnList="book_isbn"),@Index(columnList="reader_id")}) // indexes= {@Index(columnList="")} used to create indexes through names of columnlist
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int recordId;
    @Column(name="date_picking_up") // creates name of the column in DB
    LocalDate datePickingingUp;
    @Column(name="date_of_returning")
    @Setter LocalDate dateOfReturning=null;
    @Setter int daysDelayed=0;
    @Setter boolean bookIsLost=false;

    @ManyToOne
    @JoinColumn(name="book_isbn") //this column connect two tables
     BookEntity book;

    @ManyToOne
    @JoinColumn(name="reader_id") //this column connect two tables
    ReaderEntity reader;

	public RecordEntity(LocalDate datePickingingUp, BookEntity book, ReaderEntity reader) {
		super();
		this.datePickingingUp = datePickingingUp;
		this.book = book;
		this.reader = reader;
	}
    
    
}
