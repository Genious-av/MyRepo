package telran.library.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.library.domain.entities.ReaderEntity;
import telran.library.dto.Reader;

public interface ReaderRepository extends JpaRepository<ReaderEntity, Long> {
	//SQL query
//	@Query(value="SELECT * FROM readers WHERE id IN "
//			+ "(SELECT id FROM  records  WHERE "
//			+ "date_picking_up BETWEEN :from AND :to"
//			+ " GROUP BY id HAVING COUNT(*)=("
//			+ "SELECT MAX(counter) FROM "
//			+ "(SELECT COUNT(*) as counter from records where date_picking_up BETWEEN"
//			+ " :from and :to GROUP BY reader_id)))"
//			
//			
//			,nativeQuery=true)
	List<ReaderEntity> getMostActiveReaders(@Param("from") LocalDate from, @Param("to")LocalDate to);

//	@Query("SELECT reader FROM ReaderEntity reader WHERE size(records)="
//			+ "(SELECT MAX(counter) FROM ReaderRecords)") //JPQA query
//	List<ReaderEntity> getMostActiveReaders();
}
