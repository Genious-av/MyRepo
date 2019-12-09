package telran.logs.processing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.logs.processing.api.LogsApiConstants;
import telran.logs.processing.entities.MethodCount;
import telran.logs.processing.service.interfaces.ILogsProcessor;


@RestController
public class LogsMongoController {
	@Autowired
	ILogsProcessor logsProcessor;
@GetMapping(value=LogsApiConstants.EXCEPTIIONS_PERCENT)
double getExceptionsPercent() {
	return logsProcessor.getExceptionsPercent();
}
@GetMapping(value=LogsApiConstants.MOST_TIME_CONSUMING)
List<String> getTimeConsumingMethods(@RequestParam(name=LogsApiConstants.N_METHODS,
defaultValue="2")int nMethods) {
	return logsProcessor.getMostTimeConsuming(nMethods);
}
@GetMapping(value=LogsApiConstants.METHODS_NOT_RESPONSE)
List<MethodCount> getMethodsCountsNotResponse
(@RequestParam (name=LogsApiConstants.NOT_RESPONSE,defaultValue="OK") String response) {
	return logsProcessor.getMethodsCountNotResponse(response);
}


}
