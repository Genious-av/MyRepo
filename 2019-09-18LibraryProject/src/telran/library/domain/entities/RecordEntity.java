package telran.library.domain.entities;

import lombok.*;

import javax.persistence.*;

import org.springframework.stereotype.Indexed;

import java.time.LocalDate;

@NoArgsConstructor
@EqualsAndHashCode(of = "recordId")
@ToString(exclude = {"book", "reader"})
@Getter
@Setter
@Entity
@Table(name = "records",indexes= {@Index(columnList="date_of_returning"),
		@Index(columnList="book_isbn"), @Index(columnList="reader_id")})
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int recordId;
    @Column(name="date_picking_up")
    LocalDate datePickingingUp;
    @Column(name="date_of_returning")
    LocalDate dateOfReturning=null;
     int daysDelayed=0;
   boolean bookIsLost=false;

    @ManyToOne
    @JoinColumn(name="book_isbn")
     BookEntity book;

    @ManyToOne
    @JoinColumn(name="reader_id")
    ReaderEntity reader;

	public RecordEntity(LocalDate datePickingingUp, BookEntity book, ReaderEntity reader) {
		super();
		this.datePickingingUp = datePickingingUp;
		this.book = book;
		this.reader = reader;
	}
    
    
}
