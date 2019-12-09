package assign.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import assign.moduleTimeZone.service.IServiceTimezone;
import assign.moduleWeather.service.IServiceWeather;
import assign.server.config.ServerAPI;
import assign.server.response.RequestResponse;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController

public class RestController {
	@Autowired
	IServiceWeather serviceWeather;
	
	@Autowired
	IServiceTimezone serviceTimeZone;
	
	
	@PostMapping(value = ServerAPI.GET_INFO,  consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	 public String getInfo(@RequestBody(required=false)  RequestResponse body, @RequestHeader(required=false) HttpHeaders  headers,@RequestParam(required=false) String query) {
		
		if(body!=null) {
			ObjectMapper mapper = new ObjectMapper(); 
			String pocket="";
			try {
				pocket=mapper.writeValueAsString(body);
			}catch(HttpMessageNotReadableException e) {
				return "ERROR: Wrong message";
			}catch (JsonParseException e) {
				return "ERROR: Wrong JSON/XML";
			}catch (Exception e) {
				return "ERROR: OverallError";
			}
			return serviceTimeZone.getData(headers,pocket);
		}
		return serviceWeather.getData(headers, query);
	 }
}
