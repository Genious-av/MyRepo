package assign.moduleTimeZone.service;

import lombok.AllArgsConstructor;


public interface ServiceData {
	String key = "88e5mptqjmW4h1HcBOvfvDNfNzwS3osO064LwI6RmWqjRRF5ErYIadMLTWdt8UrB";
	String authError="ERROR:Authorization error";
	String badrequest="ERROR:BadRequest";
	String wrongCity="ERROR:Wrong city code";
	String otherError="ERROR:Some other error";
	String serverError="ERROR: Server error";
	String zipFormatError="ERROR: ZIP format error";
	String timeoutError="ERROR: timeout error";
	String url="http://www.zipcodeapi.com/rest/"+key+"/info.json/";
	String urlEnd="/degrees";
}
