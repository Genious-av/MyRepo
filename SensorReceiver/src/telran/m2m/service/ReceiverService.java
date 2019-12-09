package telran.m2m.service;



import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.m2m.dto.Sensor;

@EnableBinding(Sink.class) ////information for Spring which is the type of interface - physical channels is developed on base of logic channels - for input
public class ReceiverService {
	ObjectMapper mapper=new ObjectMapper();
	
	@StreamListener(Sink.INPUT)
	public void takeSensorData(String strSensor) throws Exception {
		Sensor sensor=mapper.readValue(strSensor, Sensor.class);
		System.out.printf("seqNumber:%d; sensorid: %d; delay: %d\n",sensor.getSeqNumber(), sensor.getId(), System.currentTimeMillis()-sensor.getTimestamp() );
		Thread.sleep(3000);
		
		
	}
}
