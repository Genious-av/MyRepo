package telran.logs.processing.repo;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.logs.processing.entities.LogDoc;

public interface LogsRepo extends MongoRepository<LogDoc, Serializable>,
LogStatistics
{
long countByExceptionNotNull();
}
