package telran.library.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select count(*) as counter from records group by reader_id")
public class ReaderRecords {
	@Id
	@GeneratedValue
	int id;
	long counter;

	public ReaderRecords() {
	}

	public ReaderRecords(long counter) {
		super();
		this.counter = counter;
	}

}
