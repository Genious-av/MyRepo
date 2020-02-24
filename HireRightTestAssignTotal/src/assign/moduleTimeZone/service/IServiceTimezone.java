package assign.moduleTimeZone.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import assign.moduleTimeZone.response.RequestResponse;


public interface IServiceTimezone {
	public String getData(HttpHeaders  headers,String body);
}
 