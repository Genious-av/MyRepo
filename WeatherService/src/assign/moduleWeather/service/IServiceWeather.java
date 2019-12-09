package assign.moduleWeather.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import assign.moduleWeather.response.RequestResponse;

@Component
public interface IServiceWeather {
	public String getData(HttpHeaders  headers, String query);
}
 