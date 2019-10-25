package telran.library.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("SELECT count(*) as counter FROM records GROUP BY reader_id") //uses long gounter from reader entity
public class ReaderRecords {
	@Id
	@GeneratedValue
	int id;
	long counter;
	
}
