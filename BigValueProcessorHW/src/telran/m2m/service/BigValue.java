package telran.m2m.service;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.m2m.dto.Sensor;




@EnableBinding(Sink.class)
public class BigValue {
		ObjectMapper mapper=new ObjectMapper();
		@Autowired
		Sink bigValues;
		
		@StreamListener(Sink.INPUT)
		 void takeSensorData(String sensorStrData) throws JsonParseException, JsonMappingException, IOException {
			Sensor sensor=mapper.readValue(sensorStrData, Sensor.class);
			System.out.println(sensor);
			return;
		}
}
