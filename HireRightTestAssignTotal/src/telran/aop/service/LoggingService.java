package telran.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.aop.document.ResDoc;
import telran.aop.repo.ResRepo;
@Service
public class LoggingService implements ILoggingService {
	@Autowired
	ResRepo resRepo;

	@Override
	public boolean addLog(ResDoc res) {
		resRepo.save(res);
		return true;
	}

}
