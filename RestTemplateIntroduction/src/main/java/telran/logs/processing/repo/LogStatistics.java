package telran.logs.processing.repo;

import java.util.List;

import telran.logs.processing.entities.MethodCount;

public interface LogStatistics {
List<MethodCount> mostTimeConsumingMethods(int nMethods);
List<MethodCount> methodsCountsNotResponse(String responseCode);
}
