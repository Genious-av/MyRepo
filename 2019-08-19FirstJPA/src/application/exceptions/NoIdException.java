package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NO_CONTENT, reason="NO SUCH ID")
public class NoIdException extends Exception{
	
	public  NoIdException(String msg) {
		super(msg);
	}

}
