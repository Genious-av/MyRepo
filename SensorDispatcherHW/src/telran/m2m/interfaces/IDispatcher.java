package telran.m2m.interfaces;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;

public interface IDispatcher extends Processor{
	@Output
	MessageChannel allValues();	
	
	
@Output
MessageChannel bigValues();

@Output
MessageChannel	 smallValues();
	
}
