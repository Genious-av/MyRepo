package telran.m2m.service;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.m2m.dto.Sensor;
import telran.m2m.interfaces.IDispatcher;

@EnableBinding(IDispatcher.class)
public class DispatcherService {
		ObjectMapper mapper=new ObjectMapper();
		@Value("${min_allowed_value:40}")
		int minAllowedValue;
		
		@Value("${max_allowed_value:210}")
		int maxAllowedValue;
	
		@Autowired
		IDispatcher dispatcher;
		
		@StreamListener(IDispatcher.INPUT)
		String takeSensorData(String sensorStrData) throws JsonParseException, JsonMappingException, IOException {
			dispatcher.output().send(MessageBuilder.withPayload(sensorStrData).build());
			
			Sensor sensor=mapper.readValue(sensorStrData, Sensor.class);
			if(sensor.value<minAllowedValue){
				dispatcher.smallValues()
				.send(MessageBuilder.withPayload(sensorStrData).build());
			}
			else if(sensor.value>maxAllowedValue) {
				dispatcher.bigValues()
				.send(MessageBuilder.withPayload(sensorStrData).build());
			}
			return "";
		}
}
