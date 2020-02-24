package telran.m2m.service;


import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

import telran.m2m.dto.SensorReducedData;
import telran.m2m.dto.SensorTransformedData;


@EnableBinding(Processor.class)
public class ReducerService {
		ObjectMapper mapper=new ObjectMapper();
		HashMap<Integer, SensorReducedData> keyArch = new HashMap<>();
				
		@Autowired
		Processor reducer;
		
		@StreamListener(Processor.INPUT)
		void takeMapperData(String sensorStrData) throws JsonParseException, JsonMappingException, IOException {
					System.out.println("GET "+ sensorStrData);
			SensorTransformedData sensor=mapper.readValue(sensorStrData, SensorTransformedData.class);
			
			if(keyArch.containsKey(sensor.getSensorId())) {
				if(!Arrays.equals(keyArch.get(sensor.getSensorId()).getSensorValue(),sensor.getSensorValue())){
					System.out.println("Sensor contains, current sensor value is not equals to previous sensor value");
					SensorReducedData updatedSensorReducedData=new SensorReducedData(sensor.getSensorId(),
							sensor.getSensorValue(),
							keyArch.get(sensor.getSensorId()).getSensorValue(),
							sensor.getTimestamp(),
							keyArch.get(sensor.getSensorId()).getTimestamp(),
							sensor.getSensorType());
					keyArch.put(sensor.getSensorId(), updatedSensorReducedData);
					System.out.println("POST "+ updatedSensorReducedData);
					reducer.output().send(MessageBuilder.withPayload(updatedSensorReducedData).build());
			}
			} else {
				
				double[] nullValues=new double[sensor.getSensorValue().length];
				SensorReducedData newSensorReducedData=new SensorReducedData(sensor.getSensorId(),
						sensor.getSensorValue(),
						nullValues,
						sensor.getTimestamp(),
						0l, 
						sensor.getSensorType());
				keyArch.put(sensor.getSensorId(), newSensorReducedData);
				System.out.println("POST "+ newSensorReducedData);
				reducer.output().send(MessageBuilder.withPayload(newSensorReducedData).build());
			}
			
		}
}
