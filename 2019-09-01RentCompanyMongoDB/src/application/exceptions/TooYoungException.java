package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooYoungException extends RuntimeException {
	
	public TooYoungException(String msg) {
		super(msg);
	}
}
