package telran.library.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.domain.entities.ReaderEntity;

public interface IReaderEntityRepo extends JpaRepository<ReaderEntity, Long>{

}
