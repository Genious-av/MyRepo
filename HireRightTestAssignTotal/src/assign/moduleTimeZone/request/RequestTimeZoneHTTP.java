package assign.moduleTimeZone.request;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.stream.Collectors;

import assign.moduleTimeZone.service.ServiceData;


public class RequestTimeZoneHTTP {
	
	private static final double PROBABILITY_OF_ISSUE = 0.05;

	public String getTimeZone(String ZipCode) throws IOException {
		//check for correct CodeFormat
		if(checkCityCode(ZipCode)==false) {
			return ServiceData.zipFormatError;
		}
		
		
		String url=ServiceData.url;
		int errorCode;
		
			//simulation of network issues 40x
		 if(Math.random()<PROBABILITY_OF_ISSUE) { url=url+"x"; }
		 
		 
		String urlQuery=url+ZipCode+ServiceData.urlEnd;
		
		//Getting connection
		HttpURLConnection connection = (HttpURLConnection) new URL(urlQuery).openConnection();
		
		//simulation timeout network issue
		if(Math.random()<PROBABILITY_OF_ISSUE) { connection.setConnectTimeout(1); }
		
		//Getting data from WEB
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
		    System.out.println("Buffered reader: "+ result);
		    return result;
		}
}

	private boolean checkCityCode (String cityCode) {
		if(cityCode.length()!=5) return false;
			 try {
			Integer.parseInt(cityCode);
			 	return true;
		  } catch (NumberFormatException e) {
		        return false;
		  }
	
	}
}
