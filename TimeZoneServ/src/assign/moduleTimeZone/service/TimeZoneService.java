package assign.moduleTimeZone.service;

import java.io.IOException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.apache.catalina.connector.Request;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import assign.moduleTimeZone.request.RequestTimeZoneHTTP;
import assign.moduleTimeZone.response.RequestResponse;

@Component
public class TimeZoneService implements IServiceTimezone{

	
	@Override
	public String getData(HttpHeaders headers, String body) {
		RequestTimeZoneHTTP requestW = new RequestTimeZoneHTTP();
		ObjectMapper mapper=new ObjectMapper();
			try {
				
				//mapping json to object
				RequestResponse request=mapper.readValue(body, RequestResponse.class);
				return requestW.getTimeZone(request.getRequest());
			} catch (JsonParseException e) {
			} catch (JsonMappingException e) {
			} catch (IOException e) {}
		
		return null;
	}

	

}
