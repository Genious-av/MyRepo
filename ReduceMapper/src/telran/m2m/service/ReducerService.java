package telran.m2m.service;


import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.support.MessageBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.m2m.dto.Sensor;


@EnableBinding(Processor.class)
public class ReducerService {
		ObjectMapper mapper=new ObjectMapper();
		long timeStart=System.currentTimeMillis();
		long numOfSent=1;
		int borderPeriod=10000;
	
		List<Integer> temp=new ArrayList<>();
				
		@Autowired
		Processor reducer;
		
		@StreamListener(Processor.INPUT)
		void takeSensorData(String sensorStrData) throws JsonParseException, JsonMappingException, IOException {
			reducer.output().send(MessageBuilder.withPayload(sensorStrData).build());
			//System.out.println(numOfSent);
			Sensor sensor=mapper.readValue(sensorStrData, Sensor.class);
			long timeNow=System.currentTimeMillis();
			
			//System.out.println("time start: "+(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStart), TimeZone.getDefault().toZoneId())));
			
			//System.out.println("time now: "+(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeNow), TimeZone.getDefault().toZoneId())));
			
			
			
			if(timeNow-timeStart<borderPeriod*(numOfSent-1)) {
				temp.add(sensor.getValue());
			}
			else {
				temp.clear();
				temp.add(sensor.getValue());
				numOfSent++;
			}
			
			double avg=temp.stream().mapToInt(sens->sens).average().orElse(0);
			
			Sensor avgSensor=new Sensor(sensor.id, (int)avg, timeStart+borderPeriod*(numOfSent-1));
			
			System.out.println(avgSensor);	

			return ;
		}
}
