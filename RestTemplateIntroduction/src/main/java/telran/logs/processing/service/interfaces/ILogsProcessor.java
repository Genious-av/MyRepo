package telran.logs.processing.service.interfaces;

import java.util.List;

import telran.logs.processing.entities.MethodCount;

public interface ILogsProcessor {
double getExceptionsPercent();
List<String> getMostTimeConsuming(int nMethods);
List<MethodCount> getMethodsCountNotResponse(String responseCode);
}
