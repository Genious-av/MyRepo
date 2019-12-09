package telran.m2m.service;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import telran.m2m.repo.AVGRepo;


@EnableBinding(Sink.class)
public class DBPopulatorService {
		ObjectMapper mapper=new ObjectMapper();
	
		
		@Autowired
		Sink populator;
		
		@Autowired
		AVGRepo avgRepo;
		
		@StreamListener(Sink.INPUT)
		void takeSensorData(String sensorStrData) throws JsonParseException, JsonMappingException, IOException  {
			ObjectMapper mapper=new ObjectMapper();
			Sensor sensor=mapper.readValue(sensorStrData, Sensor.class);
			System.out.println(sensor);
			avgRepo.save(sensor);
		return ;
		}
}
