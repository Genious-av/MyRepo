package telran.security.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler { // ResponseEntityExceptionHandler - This base class provides an @ExceptionHandler method for handling internal Spring MVC exceptions


    // @Validate For Validating Path Variables and Request Parameters
    @ExceptionHandler(ConstraintViolationException.class) //handler for ConstraintViolationException - exception caused by wrong data
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value()); //Sends an error response to the client using the specified status code 
    }

    // error handler for @Valid
    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex, // handleMethodArgumentNotValid - customize the response for MethodArgumentNotValidException(Exception to be thrown when validation on an argument annotated with @Valid fails.)
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult() //getBindingResult Return the results of the failed validation.
                .getAllErrors()
                .stream()
                .filter(objectError -> objectError.getClass().getName().contains("SpringValidatorAdapter"))
                .map(x -> {
                    String msg = x.getDefaultMessage();
                    try {
                        return msg + ": " + ((FieldError) x).getRejectedValue();
                    } catch (Exception e) {
                        return msg;
                    }
                })
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status); //this function un case of throwing exception create object of time, ststus and text of error

    }

}