package telran.m2m.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.m2m.dto.Sensor;



@EnableBinding(Source.class) //information for Spring which is the type of interface - physical channels is developed on base of logic channels -for output
public class GeneratorService {
	
	//range of values
	@Value("${min_value:30}")
	int minValue;
	
	@Value("${max_value:250}")
	int maxValue;
	
	@Value("${nSensors:10}")
	int nSensors;
	
	ObjectMapper mapper=new  ObjectMapper();
	

	@InboundChannelAdapter(Source.OUTPUT) //by default every second start this function and send return to output(for this app output is KAFKA)
	public String randomSensorData() throws JsonProcessingException {
		String strSensor=mapper.writeValueAsString(getRandomSensor());
		return strSensor;
	}
	private Object getRandomSensor() {
		int id=getRandomNumber(1,nSensors);
		int value=getRandomNumber(minValue,maxValue);
		System.out.println(id+" " +  value+" " +  System.currentTimeMillis());
		
		return new Sensor(id, value, System.currentTimeMillis());
	}
	
	private int getRandomNumber(int min, int max) {
		
		return (int)(min+Math.random()*(max-min+1));
	}
}
