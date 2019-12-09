package telran.logs.processing.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.logs.processing.entities.MethodCount;
import telran.logs.processing.repo.LogsRepo;
import telran.logs.processing.service.interfaces.ILogsProcessor;
@Service
public class LogsProcessor implements ILogsProcessor {
@Autowired
LogsRepo logsRepo;
	@Override
	public double getExceptionsPercent() {
		long countAll = logsRepo.count();
		long countExceptions = logsRepo.countByExceptionNotNull();
		return (double)countExceptions / countAll * 100;
	}

	@Override
	public List<String> getMostTimeConsuming(int nMethods) {
		
		return logsRepo.mostTimeConsumingMethods(nMethods)
				.stream().map(MethodCount::getMethodName)
				.collect(Collectors.toList());
	}

	@Override
	public List<MethodCount> getMethodsCountNotResponse(String responseCode) {
		return logsRepo.methodsCountsNotResponse(responseCode);
	}

}
