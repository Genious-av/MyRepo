package telran.library.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.library.domain.entities.ReaderEntity;

public interface ReaderRepository extends JpaRepository<ReaderEntity, Long> {

	@Query(value="select * from readers where id in "
			+ "(select reader_id from  records  where "
			+ "date_picking_up between :from and :to"
			+ "group by reader_id having count(*)=("
			+ "select max(counter) from "
			+ "(select count(*) as counter from records where date_picking_up between"
			+ " :from and :to group by reader_id)))",nativeQuery=true)
	List<ReaderEntity> getMostActiveReaders(@Param("from")LocalDate from,
			@Param("to")LocalDate to);
	@Query("select reader from ReaderEntity reader where size(records)="
			+ "(select max(counter) from ReaderRecords)")
	List<ReaderEntity> getMostActiveReaders();


}
