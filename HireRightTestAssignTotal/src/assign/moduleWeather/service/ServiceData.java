package assign.moduleWeather.service;

import lombok.AllArgsConstructor;


public interface ServiceData {
	String key = "&appid=cfc4a7e5e20eefd2c0ed27f18cd0f1de";
	String authError="ERROR:Authorization error";
	String badrequest="ERROR:BadRequest";
	String wrongCity="ERROR:Wrong city code";
	String otherError="ERROR:Some other error";
	String serverError="ERROR: Server error";
	String cityFormatError="ERROR: city format error";
	String timeoutError="ERROR: timeout error";
	String url="http://api.openweathermap.org/data/2.5/forecast?id=";
}
