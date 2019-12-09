package assign.moduleWeather.service;

import java.io.IOException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import assign.moduleWeather.request.RequestWeatherHTTP;
import assign.moduleWeather.response.RequestResponse;

@Service
public class WeatherService implements IServiceWeather{

	@Override
	public String getData(HttpHeaders headers,  String query) {
		RequestWeatherHTTP requestW = new RequestWeatherHTTP();
		String res="";
		try {
			res = requestW.getWeather(query);
		} catch (IOException e) {}
			
	return res;
	}

	

}
