package assign.moduleWeather.request;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import assign.moduleWeather.service.ServiceData;

@Component
public class RequestWeatherHTTP {
	
	private static final double PROBABILITY_OF_ISSUE = 0.05;

	public String getWeather(String cityCode) throws IOException {
		
		if(checkCityCode(cityCode)==false) return ServiceData.cityFormatError;
		String key=ServiceData.key;
		String url=ServiceData.url;
		int errorCode;
		
			//simulation of network issues 40x
		  if(Math.random()<PROBABILITY_OF_ISSUE) { key=key+"x"; }
		  if(Math.random()<PROBABILITY_OF_ISSUE) { url=url+"x"; }
		 
		String urlQuery=url+cityCode+key;
		HttpURLConnection connection = (HttpURLConnection) new URL(urlQuery).openConnection();
		
		//simulation timeout network issue
		if(Math.random()<PROBABILITY_OF_ISSUE) { connection.setConnectTimeout(1); }
		
		
		try {
			errorCode = connection.getResponseCode();
		} catch (SocketTimeoutException e) {
			return ServiceData.timeoutError;
		}
		
		if(errorCode<300) {}
		else if(errorCode==400) return ServiceData.badrequest;
		else if(errorCode==401) return ServiceData.authError;
		else if(errorCode==404) return ServiceData.wrongCity;
		else if(errorCode<500) 	return ServiceData.otherError;
		else if(errorCode>=500) return ServiceData.serverError;
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
		    String result = br.lines().collect(Collectors.joining("\n"));
		    return result;
		}
		
	}

	private boolean checkCityCode (String cityCode) {
		if(cityCode.length()<5 ||cityCode.length()>10) return false;
			 try {
			Integer.parseInt(cityCode);
		  	return true;
		  } catch (NumberFormatException e) {
		        return false;
		  }
	
	}
}
