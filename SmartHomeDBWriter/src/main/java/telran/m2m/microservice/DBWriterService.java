package telran.m2m.microservice;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.m2m.dto.Sensor;
import telran.m2m.repo.IMongoRepository;

@EnableBinding(Sink.class) //connect to input channel
public class DBWriterService {
	
	@Autowired
	IMongoRepository mongoRepo;
	
	ObjectMapper jsonMapper = new ObjectMapper();
	
	@StreamListener(Sink.INPUT)
	public void takeSensorData(String strData) throws IOException
	{
		System.out.println("strData: " + strData);
		Sensor sensor = jsonMapper.readValue(strData, Sensor.class);
		mongoRepo.save(sensor);
	}
}